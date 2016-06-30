package modulo4.ddam.markmota.tk.practica2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import modulo4.ddam.markmota.tk.practica2.R;
import modulo4.ddam.markmota.tk.practica2.models.ModelApp;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;

/**
 * Created by markmota on 6/28/16.
 */
public class AdapterItemList extends ArrayAdapter<ModelApp>{
    public AdapterItemList(Context context, List<ModelApp> objects) {
        super(context, 0, objects);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView appName= (TextView) convertView.findViewById(R.id.row_list_appName);
        TextView appDesc= (TextView) convertView.findViewById(R.id.row_list_appDesc);
        TextView appDev= (TextView) convertView.findViewById(R.id.row_list_appDev);
        TextView appStatus= (TextView) convertView.findViewById(R.id.row_list_appStatus);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_list_row_image_view );
        ProgressBar loading=(ProgressBar)convertView.findViewById(R.id.row_list_img_loader);
        ModelApp modelApp=getItem(position);
        appName.setText(modelApp.name);
        appDesc.setText(modelApp.description);
        appDev.setText(modelApp.developer);
        ModelImage images=new ModelImage();
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
                Picasso.with(parent.getContext())
                        .load(imageToUse)
                        .resize(60, 60)
                        .centerCrop()
                        .placeholder(android.R.drawable.ic_input_get)
                        .error(android.R.drawable.ic_dialog_alert)
                        .into(img);
                loading.setVisibility(View.GONE);
                break;
            default: loading.setVisibility(View.VISIBLE);
                break;
        }


        return convertView;
    }
}
