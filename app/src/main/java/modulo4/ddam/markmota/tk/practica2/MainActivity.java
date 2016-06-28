package modulo4.ddam.markmota.tk.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import modulo4.ddam.markmota.tk.practica2.models.ModelImage;

public class MainActivity extends AppCompatActivity {

    ModelImage images=new ModelImage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img= (ImageView) findViewById(R.id.main_activity_img);
        int random=images.getRandom();
        String imageToUse=images.getImage(random);
        Picasso.with(getApplicationContext()).load(imageToUse).resize(100, 100).centerCrop().into(img);

    }
}
