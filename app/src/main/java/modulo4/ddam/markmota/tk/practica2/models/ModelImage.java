package modulo4.ddam.markmota.tk.practica2.models;

import java.util.Random;

/**
 * Created by markmota on 6/27/16.
 */
public class ModelImage {

    public final static String[] APP_IMAGES = {
            "http://cdn2.business2community.com/wp-content/uploads/2015/11/icon.png",
            "http://clappingmusicapp.com/assets/Products/Icons/icon-clappingmusic.png",
            "http://www.ijailbreak.com/wp-content/uploads/2013/12/iOS-7-Cydia-Icon-3.png",
            "https://lh5.ggpht.com/ly6G6lUNHjWKvLgeHO0-ilg7zkEXc-hCWP0Q94gdyCeejWNoDMw6h5buM0pFO0mSDKQ=w300",
            "http://s3.amazonaws.com/totem_production/assets/logos/3963/original/Saga_Icon_1024x1024.png?1366835171"
    };


    public String getImage(){
        Random rand = new Random();
        int num_images=APP_IMAGES.length;
        int rndNumber=rand.nextInt(num_images);
        return APP_IMAGES[rndNumber];
    }


}
