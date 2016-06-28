package modulo4.ddam.markmota.tk.practica2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.util.Date;

import modulo4.ddam.markmota.tk.practica2.models.ModelApp;

/**
 * Created by markmota on 6/27/16.
 */
public class AppsDataSource {
    private final SQLiteDatabase db;

    public AppsDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public boolean saveItem(ModelApp modelApp)
    {


        String name=modelApp.name ;
        String description=modelApp.description;
        String developer=modelApp.developer ;
        int image= modelApp.image  ;
        String installed= modelApp.installed ;
        String edited= modelApp.edited ;
        String updated= modelApp.updated ;


        // Fist we search if there is and app with the same name in the database
        String[] fields_to_recover = new String[] {MySqliteHelper.COLUMN_ID};
        String[] args = new String[] {name};
        String where= MySqliteHelper.APP_COLUMN_NAME+"=? ";
        Cursor cursor =db.query(MySqliteHelper.APP_TABLE_NAME,fields_to_recover,where,args,null,null,null);
        if (!cursor.moveToNext())
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MySqliteHelper.APP_COLUMN_NAME,name);
            contentValues.put(MySqliteHelper.APP_COLUMN_DESC,description);
            contentValues.put(MySqliteHelper.APP_COLUMN_DEV,developer);
            contentValues.put(MySqliteHelper.APP_COLUMN_IMG,image);
            contentValues.put(MySqliteHelper.APP_COLUMN_INSTALLED,installed);
            contentValues.put(MySqliteHelper.APP_COLUMN_EDITED,edited);
            contentValues.put(MySqliteHelper.APP_COLUMN_UPDATED,updated);
            db.insert(MySqliteHelper.APP_TABLE_NAME,null,contentValues);
            return true;
        }
        return false;
    }
    public void editItem(ModelApp modelApp){


        int id=modelApp.id;
        String name=modelApp.name ;
        String description=modelApp.description;
        String developer=modelApp.developer ;
        int image= modelApp.image  ;
        String installed= modelApp.installed ;
        String edited= modelApp.edited ;
        String updated= modelApp.updated;



        ContentValues fields_to_update = new ContentValues();
        fields_to_update.put(MySqliteHelper.APP_COLUMN_NAME,name);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_DESC,description);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_DEV,developer);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_IMG,image);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_INSTALLED,installed);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_EDITED,edited);
        fields_to_update.put(MySqliteHelper.APP_COLUMN_UPDATED,updated);


        String[] args = new String[] {String.valueOf(id)};
        String where= MySqliteHelper.COLUMN_ID+"=? ";
        db.update(MySqliteHelper.APP_TABLE_NAME,fields_to_update,where,args);



    }

    public ModelApp getInfoItem(int id){

        ModelApp modelApp;

        String[] fields_to_recover = new String[] {
                MySqliteHelper.APP_COLUMN_NAME,
                MySqliteHelper.APP_COLUMN_DESC,
                MySqliteHelper.APP_COLUMN_DEV,
                MySqliteHelper.APP_COLUMN_IMG,
                MySqliteHelper.APP_COLUMN_EDITED,
                MySqliteHelper.APP_COLUMN_INSTALLED,
                MySqliteHelper.APP_COLUMN_UPDATED,
                MySqliteHelper.APP_COLUMN_EDITED,
                MySqliteHelper.APP_COLUMN_STATUS
        };
        String[] args = new String[] {String.valueOf(id)};
        String where= MySqliteHelper.COLUMN_ID+"=? ";
        Cursor cursor =db.query(MySqliteHelper.APP_TABLE_NAME,fields_to_recover,where,args,null,null,null);
        if (cursor.moveToNext())
        {

            String name=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_NAME));
            String description=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DESC));
            String developer=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DEV));
            int image=cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_IMG));
            String installed= cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_INSTALLED));
            String edited= cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_EDITED));
            String updated= cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_UPDATED));
            int status= cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_STATUS));

            modelApp = new ModelApp(id,name,description,developer,image,installed,edited,updated,status);

            return  modelApp;
        }
        else{
            return null;
        }



    }

    public boolean editStatusItem(int id,int status){
        if(status>=0 && status <=3){
            ContentValues fields_to_update = new ContentValues();
            fields_to_update.put(MySqliteHelper.APP_COLUMN_STATUS,status);
            String[] args = new String[] {String.valueOf(id)};
            String where= MySqliteHelper.COLUMN_ID+"=? ";
            db.update(MySqliteHelper.APP_TABLE_NAME,fields_to_update,where,args);
        }
        return false;
    }




}
