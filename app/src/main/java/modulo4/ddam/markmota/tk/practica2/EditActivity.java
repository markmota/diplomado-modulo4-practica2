package modulo4.ddam.markmota.tk.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
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
    // Generating random image
    private ModelImage modelImage= new ModelImage();
    private int image=modelImage.getRandom();
    private AppsDataSource appsDataSource;

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
        findViewById(R.id.activity_edit_btn_edit).setOnClickListener(new View.OnClickListener(){
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

        setSupportActionBar(toolbar);
        // back icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        String installed=String.valueOf(timestamp);


        if(TextUtils.isEmpty(name)){
            showMessage(R.string.activity_edit_message_empty_name);
        }
        else if(TextUtils.isEmpty(desc)){
            showMessage(R.string.activity_edit_message_empty_desc);
        }else if(TextUtils.isEmpty(dev)){
            showMessage(R.string.activity_edit_message_empty_dev);
        }
        else{
            if(appUpdaded.isChecked())
                updated=String.valueOf(timestamp);
            else
                updated=null;

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
    // Showing toast messages
    private void showMessage(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }
}
