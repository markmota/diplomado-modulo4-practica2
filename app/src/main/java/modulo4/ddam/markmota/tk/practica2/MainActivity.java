package modulo4.ddam.markmota.tk.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import modulo4.ddam.markmota.tk.practica2.fragments.FragmentList;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting support to Action Bar
        setSupportActionBar(toolbar);
        chargeFragmentListApps();

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_apps, menu);
        return true;
    }

    // Implements list fragment functionality
    private void chargeFragmentListApps() {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,new FragmentList()).commit();
    }
}
