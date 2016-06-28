package modulo4.ddam.markmota.tk.practica2.models;

import java.util.Random;

/**
 * Created by markmota on 6/27/16.
 */
public class ModelImage {

    public final static String[] APP_IMAGES = {
            "http://designroast.org/wp-content/uploads/2014/01/flat-duolingo.png",
            "http://tapbots.com/img/apps/icon_tweetbot_ios_256.png",
            "http://droplr.com/wp-content/uploads/2014/12/ios-icon1.png",
            "http://a4.mzstatic.com/us/r30/Purple60/v4/02/14/1c/02141cde-cd75-8302-b16a-a19250a7bcd1/icon256.png",
            "http://a1.mzstatic.com/eu/r30/Purple69/v4/8c/25/ff/8c25ffe7-079f-942c-67eb-4b83e7bb6e2a/icon128-2x.png",
            "http://a1.mzstatic.com/us/r30/Purple1/v4/09/45/fa/0945fabe-7610-a9d4-58e1-87af77de7ccc/icon128-2x.png",
            "https://modernatlas.co/img/Icon@2x.png",
            "http://worksofwisnu.com/theme-preview/urip/layout-v2/images/app-icon/boxes128.png",
            "http://www.morpholioapps.com/images/morpholio_board_app_icon.png",
            "http://tyme-app.com/wp-content/uploads/2016/02/app_icon_mobile_128.png"

    };


    public String getImage(int index){

        return APP_IMAGES[index];
    }
    public int getRandom(){
        Random rand = new Random();
        int num_images=APP_IMAGES.length;
        return rand.nextInt(num_images);
    }


}
