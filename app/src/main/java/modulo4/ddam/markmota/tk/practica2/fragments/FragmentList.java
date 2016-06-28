package modulo4.ddam.markmota.tk.practica2.fragments;



import android.animation.LayoutTransition;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import modulo4.ddam.markmota.tk.practica2.R;
import modulo4.ddam.markmota.tk.practica2.adapters.AdapterItemList;
import modulo4.ddam.markmota.tk.practica2.models.ModelApp;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;
import modulo4.ddam.markmota.tk.practica2.sql.AppsDataSource;


public class FragmentList extends Fragment {
    private ListView listView;
    private List<ModelApp> array= new ArrayList<>();
    private int counter;
    private AppsDataSource appsDataSource;
    private int numberOfElements;
    private final String LIST_EMPTY_IMG= "https://help.screenconnect.com/images/3/30/ApplicationIcon256.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appsDataSource=new AppsDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list,container,false);
        // list items configuration
        listView = (ListView) view.findViewById(R.id.fragment_list_listItems);
        LinearLayout emptyList= (LinearLayout) view.findViewById(R.id.fragment_list_empty_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter= (AdapterItemList) parent.getAdapter();
                ModelApp modelApp = adapter.getItem(position);
                //Intent intent= new Intent(parent.getContext(),EditAppActivity.class);
                //intent.putExtra("key_app_id",modelApp.id);
                //startActivity(intent);

            }
        });
        // Getting all the elements on the database
        List<ModelApp> modelItemList = appsDataSource.getAllItems();
        numberOfElements= modelItemList.size();
        if(numberOfElements>0){
            emptyList.setVisibility(view.GONE);
            // For the fist time populating the list with the elements in de modelItemList
            listView.setAdapter(new AdapterItemList(getActivity(),modelItemList));
        }
        else{
            ImageView img=(ImageView) view.findViewById(R.id.fragment_list_img);
            Picasso.with(getActivity()).load(LIST_EMPTY_IMG).resize(100, 100).centerCrop().into(img);

            emptyList.setVisibility(view.VISIBLE);
        }

        return view;

    }

}
