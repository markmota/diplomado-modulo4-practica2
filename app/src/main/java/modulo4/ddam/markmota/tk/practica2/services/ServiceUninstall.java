package modulo4.ddam.markmota.tk.practica2.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import modulo4.ddam.markmota.tk.practica2.R;
import modulo4.ddam.markmota.tk.practica2.models.ModelApp;
import modulo4.ddam.markmota.tk.practica2.models.ModelImage;
import modulo4.ddam.markmota.tk.practica2.sql.AppsDataSource;

public class ServiceUninstall extends Service {
    private MyAsyncTask myAsyncTask;
    private int id;
    private ModelApp modelApp;
    private ModelImage modelImage=new ModelImage();
    private AppsDataSource appsDataSource;
    private String appIcon;
    public static final String ACTION_UNINSTALLED ="modulo4.ddam.markmota.tk.practica2.ACTION_UNINSTALLED";
    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getExtras()!=null && intent.getExtras().containsKey("key_app_id"))
        {
            id=intent.getExtras().getInt("key_app_id");
            appsDataSource=new AppsDataSource(getApplicationContext());
            modelApp=appsDataSource.getInfoItem(id);
            appIcon=modelImage.getImage(modelApp.image);

        }

        if(myAsyncTask==null)
        {
            myAsyncTask= new MyAsyncTask();
            myAsyncTask.execute();
        }

        return START_STICKY;
    }



    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>
    {
        private NotificationCompat.Builder mNotif;

        @Override
        protected void onPreExecute() {

            mNotif = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle(getString(R.string.service_uninstall_label_title))
                    .setContentText(String.format(getString(R.string.service_uninstall_label_content),modelApp.name))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_alert))
                    .setSmallIcon(android.R.drawable.ic_dialog_alert);
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (int i=0;i<5;i++)
            {
                publishProgress(i);
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mNotif.setProgress(5,values[0],false);
            NotificationManager manager  = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(id,mNotif.build());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                //Delete bar progress setting to 0
                mNotif.setProgress(0,0,false);
                mNotif.setContentTitle(getString(R.string.service_uninstall_label_title_completed));
                mNotif.setContentText(String.format(getString(R.string.service_uninstall_label_completed),modelApp.name));
                mNotif.setAutoCancel(true);
                mNotif.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(String.format(getString(R.string.service_uninstall_label_completed),modelApp.name)));
                NotificationManager manager  = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(id,mNotif.build());
            }
            appsDataSource.deleteItem(modelApp.id);
            myAsyncTask=null;
            stopSelf();
            Intent i = new Intent(ACTION_UNINSTALLED);
            i.putExtra("app_id",modelApp.id);
            sendBroadcast(i);
        }
    }


}
