package modulo4.ddam.markmota.tk.practica2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;


import modulo4.ddam.markmota.tk.practica2.models.ModelApp;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;
import modulo4.ddam.markmota.tk.practica2.services.ServiceUninstall;
import modulo4.ddam.markmota.tk.practica2.services.ServiceUpdate;
import modulo4.ddam.markmota.tk.practica2.sql.AppsDataSource;

public class DetailActivity extends AppCompatActivity {
    int app_id;
    ModelApp modelApp;
    AppsDataSource appsDataSource;

    private TextView appName;
    private TextView appDesc;
    private TextView appDev;
    private TextView appStatus;
    private TextView appDesinstalled;
    private ImageView appImg;
    private ProgressBar loading;
    private Button updatedBtn;
    private Button uninstallBtn;
    private Button openBtn;
    private LinearLayout btnsLaout;
    ModelImage images=new ModelImage();

    // Crating broadcastReceivers and configuration what to do when the service ends the job
    private BroadcastReceiver broadcastReceiverUpdated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int app_id = intent.getExtras().getInt("app_id");
            //if we are in the app view not in other
            if(app_id==modelApp.id){
                chargingInfo();
            }
        }
    };
    private BroadcastReceiver broadcastReceiverUninstalled = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int app_id = intent.getExtras().getInt("app_id");
            //if we are in the app view not in other
            if(app_id==modelApp.id){
                appDesinstalled.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                appImg.setVisibility(View.VISIBLE);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Getting the info sended from the precious activity
        app_id=getIntent().getExtras().getInt("key_app_id");
        // Getting app  database information
        appsDataSource= new AppsDataSource(getApplicationContext());

        // Setting layout elements
        appName=(TextView) findViewById(R.id.activity_detail_name);
        appDesc=(TextView)findViewById(R.id.activity_detail_desc);
        appDev=(TextView)findViewById(R.id.activity_detail_dev);
        appStatus=(TextView)findViewById(R.id.activity_detail_status);
        appImg= (ImageView) findViewById(R.id.activity_detail_image);
        loading=(ProgressBar)findViewById(R.id.activity_detail_img_loader);
        uninstallBtn=(Button) findViewById(R.id.activity_detail_btn_uninstall);
        updatedBtn=(Button) findViewById(R.id.activity_detail_btn_update);
        openBtn=(Button) findViewById(R.id.activity_detail_btn_open);
        btnsLaout=(LinearLayout)findViewById(R.id.activity_detail_btn_layout);
        appDesinstalled=(TextView)findViewById(R.id.activity_detail_msg_desinstalled);





        //Charging info from the database to the layout
        chargingInfo();








    }

    private void chargingInfo() {

        modelApp=appsDataSource.getInfoItem(app_id);
        if(modelApp==null){
            showMessage(R.string.activity_detail_message_noApp_info);
            finish();
        }

        if(modelApp.updated!=null){
            updatedBtn.setVisibility(View.GONE);
        }
        else{
            updatedBtn.setVisibility(View.VISIBLE);
        }


        //Setting info from the database to the layout
        appName.setText(modelApp.name);
        appDesc.setText(modelApp.description);
        appDev.setText(modelApp.developer);

        String statusMsg="";
        switch (modelApp.status){
            case 0:
                statusMsg=(modelApp.updated!=null? "Updated on "+ modelApp.updated:"Installed on "+ modelApp.installed);
                break;
            case 1:
                statusMsg="Installing";
                break;
            case 2:
                statusMsg="Updating";
                break;
            case 3:
                statusMsg="De installing";
                break;
        }

        appStatus.setText(statusMsg);

        switch (modelApp.status){
            case 0:
                String imageToUse=images.getImage(modelApp.image);
                Picasso.with(getApplicationContext())
                        .load(imageToUse)
                        .resize(100, 100)
                        .centerCrop()
                        .placeholder(android.R.drawable.ic_input_get)
                        .error(android.R.drawable.ic_dialog_alert)
                        .into(appImg);
                loading.setVisibility(View.GONE);
                appImg.setVisibility(View.VISIBLE);
                btnsLaout.setVisibility(View.VISIBLE);
                break;
            default: loading.setVisibility(View.VISIBLE);
                appImg.setVisibility(View.GONE);
                btnsLaout.setVisibility(View.GONE);
                break;
        }
        // Action to de open app, it will be a url
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        // Action to the desinstall buton
        uninstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getContext())
                        .setTitle(R.string.activity_detail_uninstall_title)
                        .setMessage(String.format(getString(R.string.activity_detail_uninstall_message),modelApp.name))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Code to delete app
                                uninstallService();

                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();

            }
        });
        // Action to update button
        updatedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateService();
            }
        });


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting support to Action Bar
        toolbar.setTitle("App Detail");
        // Setting back icon
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);

        setSupportActionBar(toolbar);
        // back icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
    private  void uninstallService(){
        appsDataSource.editStatusItem(modelApp.id,3);
        Intent service=new Intent(getApplicationContext(),ServiceUninstall.class);
        service.putExtra("key_app_id",modelApp.id);
        startService(service);
        chargingInfo();

    }
    private  void updateService(){
        appsDataSource.editStatusItem(modelApp.id,2);
        Intent service=new Intent(getApplicationContext(),ServiceUpdate.class);
        service.putExtra("key_app_id",modelApp.id);
        startService(service);
        chargingInfo();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if(modelApp.status==0){
            getMenuInflater().inflate(R.menu.menu_list_edit, menu);
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.menu_app_edit:
                edit_item();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    // Showing toast messages
    private void showMessage(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }

    private void edit_item() {
        Intent intent= new Intent(getApplicationContext(),EditActivity.class);
        intent.putExtra("key_app_id",modelApp.id);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        chargingInfo();
        //Registering filters an listen the services
        IntentFilter filterUninstalled = new IntentFilter();
        filterUninstalled.addAction(ServiceUninstall.ACTION_UNINSTALLED);
        registerReceiver(broadcastReceiverUninstalled,filterUninstalled);
        IntentFilter filterUpdated = new IntentFilter();
        filterUpdated.addAction(ServiceUpdate.ACTION_UPDATED);
        registerReceiver(broadcastReceiverUpdated,filterUpdated);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Unregistering services
        unregisterReceiver(broadcastReceiverUninstalled);
        unregisterReceiver(broadcastReceiverUpdated);
    }


}
