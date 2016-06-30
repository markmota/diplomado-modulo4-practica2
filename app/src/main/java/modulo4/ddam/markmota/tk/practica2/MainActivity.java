package modulo4.ddam.markmota.tk.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import modulo4.ddam.markmota.tk.practica2.fragments.FragmentList;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting support to Action Bar
        toolbar.setTitle("App List");

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.menu_list_add_item:
                add_item();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void add_item() {
        Intent intent= new Intent(getApplicationContext(),EditActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        chargeFragmentListApps();
    }
}
