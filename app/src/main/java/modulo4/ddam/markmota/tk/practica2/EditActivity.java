package modulo4.ddam.markmota.tk.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.util.Date;

import modulo4.ddam.markmota.tk.practica2.models.ModelApp;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;
import modulo4.ddam.markmota.tk.practica2.sql.AppsDataSource;

public class EditActivity extends AppCompatActivity  {

    private  EditText appName;
    private EditText appDesc;
    private EditText appDev;
    private CheckBox appUpdaded;
    private ImageView appImg;
    private Button saveBtn;
    // Generating random image
    private ModelImage modelImage= new ModelImage();
    private int image=modelImage.getRandom();
    private AppsDataSource appsDataSource;
    private int app_id;
    private  ModelApp modelAppOrg;
    private  Boolean editApp=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);



        appsDataSource=new AppsDataSource(getApplicationContext());
        appName=(EditText)findViewById(R.id.activity_edit_name);
        appDesc=(EditText)findViewById(R.id.activity_edit_desc);
        appDev=(EditText)findViewById(R.id.activity_edit_dev);
        appUpdaded= (CheckBox) findViewById(R.id.activity_edit_updated);
        appImg= (ImageView) findViewById(R.id.activity_edit_image);
        saveBtn=(Button) findViewById(R.id.activity_edit_btn_edit);
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveInfo();
            }
        });

        Picasso.with(getApplicationContext())
                .load(modelImage.getImage(image))
                .resize(100, 100).centerCrop()
                .placeholder(android.R.drawable.ic_input_get)
                .error(android.R.drawable.ic_dialog_alert)
                .into(appImg);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting support to Action Bar
        toolbar.setTitle("Install App");
        // Setting back icon
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);

        // Getting the info sended from the precious activity
        if(getIntent().hasExtra("key_app_id")){
            app_id=getIntent().getExtras().getInt("key_app_id");
            // Set the info from the database
            setInfo();
            toolbar.setTitle("Edit App");
            saveBtn.setText(R.string.activity_edit_btn_label_change);

        }


        setSupportActionBar(toolbar);
        // back icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setInfo() {

        AppsDataSource appsDataSource=new AppsDataSource(getApplicationContext());
        modelAppOrg=appsDataSource.getInfoItem(app_id);
        editApp=true;
        //Setting info from the database to the layout
        appName.setText(modelAppOrg.name);
        appDesc.setText(modelAppOrg.description);
        appDev.setText(modelAppOrg.developer);
        if(modelAppOrg.updated!=null)
            appUpdaded.setChecked(true);

    }


    private void saveInfo() {
        // Getting current complete date and time
        boolean saved=false;
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        String name=appName.getText().toString();
        String desc=appDesc.getText().toString();
        String dev=appDev.getText().toString();
        String updated;



        if(TextUtils.isEmpty(name)){
            showMessage(R.string.activity_edit_message_empty_name);
        }
        else if(TextUtils.isEmpty(desc)){
            showMessage(R.string.activity_edit_message_empty_desc);
        }else if(TextUtils.isEmpty(dev)){
            showMessage(R.string.activity_edit_message_empty_dev);
        }
        else{


            // To edit
            if(editApp){
                // Handle the correct updated information
                if(appUpdaded.isChecked()){
                    if(modelAppOrg.updated!=null)
                        updated=modelAppOrg.updated;
                    else{
                        updated=String.valueOf(timestamp);
                    }
                }
                else{
                    updated=null;
                }

                String edited= String.valueOf(timestamp);
                String installed=modelAppOrg.installed;
                ModelApp modelApp=new ModelApp(modelAppOrg.id,name,desc,dev,image,installed,edited,updated,0);
                saved=appsDataSource.editItem(modelApp);
                if(saved){
                    showMessage(R.string.activity_edit_message_app_edited);
                    finish();
                }
                else{
                    showMessage(R.string.activity_edit_message_app_not_edited);
                }
            }
            // To save new item
            else{
                if(appUpdaded.isChecked())
                    updated=String.valueOf(timestamp);
                else
                    updated=null;
                String installed=String.valueOf(timestamp);
                ModelApp modelApp=new ModelApp(0,name,desc,dev,image,installed,null,updated,0);
                saved=appsDataSource.saveItem(modelApp);
                if(saved){
                    showMessage(R.string.activity_edit_message_app_installed);
                    finish();
                }
                else{
                    showMessage(R.string.activity_edit_message_app_not_installed);
                }
            }


        }

    }
    // Showing toast messages
    private void showMessage(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }
}
