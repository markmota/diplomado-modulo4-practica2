package modulo4.ddam.markmota.tk.practica2.models;

import java.util.Random;

/**
 * Created by markmota on 6/27/16.
 */
public class ModelImage {

    public final static String[] APP_IMAGES = {
            "http://pds24.egloos.com/pds/201212/10/78/f0256678_50c530af5c270.png",
            "https://www.appsgeyser.com/img/messenger-icon.png",
            "http://a4.mzstatic.com/us/r30/Purple60/v4/02/14/1c/02141cde-cd75-8302-b16a-a19250a7bcd1/icon256.png",
            "http://a1.mzstatic.com/eu/r30/Purple69/v4/8c/25/ff/8c25ffe7-079f-942c-67eb-4b83e7bb6e2a/icon128-2x.png",
            "http://a1.mzstatic.com/us/r30/Purple1/v4/09/45/fa/0945fabe-7610-a9d4-58e1-87af77de7ccc/icon128-2x.png",
            "http://iphone-icon.com/images/app-icon.png",
            "http://worksofwisnu.com/theme-preview/urip/layout-v2/images/app-icon/boxes128.png",
            "http://www.morpholioapps.com/images/morpholio_board_app_icon.png",
            "http://tyme-app.com/wp-content/uploads/2016/02/app_icon_mobile_128.png",
            "http://a1.mzstatic.com/eu/r30/Purple49/v4/7a/19/a4/7a19a435-7c6c-be9a-6f9d-d0cd39ac9cfb/icon128-2x.png",
            "http://a4.mzstatic.com/eu/r30/Purple49/v4/01/2f/bc/012fbc84-6cf4-e74d-1a00-72b68963256d/icon256.png",
            "http://a2.mzstatic.com/eu/r30/Purple69/v4/07/6d/8b/076d8b5b-764f-5db2-f16f-ed829d6f80a5/icon128-2x.png",
            "http://a3.mzstatic.com/eu/r30/Purple7/v4/e3/45/51/e345515e-359f-d295-3717-6bfcb676c663/icon256.png",
            "http://a2.mzstatic.com/us/r30/Purple20/v4/d4/c0/02/d4c00255-86db-810b-7ea4-1d77b59e4a53/icon256.png",
            "http://a3.mzstatic.com/eu/r30/Purple60/v4/6a/30/2f/6a302fc9-9705-96a7-aec8-2a38127a8a96/icon256.png",
            "http://a4.mzstatic.com/eu/r30/Purple49/v4/98/39/3b/98393ba6-40a4-1d28-9416-647833493f67/icon256.png",
            "http://a4.mzstatic.com/us/r30/Purple20/v4/01/a4/58/01a45806-6a6d-d741-804f-fd5e9f5fdb2a/icon128.png",
            "http://a4.mzstatic.com/eu/r30/Purple49/v4/b8/78/70/b87870d5-47a9-4794-917d-a8f3350b6915/icon128.png",
            "http://a4.mzstatic.com/us/r30/Purple30/v4/8a/66/84/8a6684db-2717-3332-4554-e9679ba5b677/icon256.png",
            "http://a2.mzstatic.com/eu/r30/Purple69/v4/26/87/a4/2687a41a-2452-604b-13a8-6922acb3f46a/icon128.png",
            "http://a3.mzstatic.com/eu/r30/Purple60/v4/c5/45/43/c54543e0-15b1-3370-77f9-a19b917a4251/icon128.png",
            "http://a3.mzstatic.com/eu/r30/Purple30/v4/8f/4d/73/8f4d7355-cb3a-47df-1d0b-de9d5e9a070a/icon128-2x.png"
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
