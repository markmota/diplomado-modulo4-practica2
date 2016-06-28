package modulo4.ddam.markmota.tk.practica2.sql;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by markmota on 6/27/16.
 */
public class MySqliteHelper  extends SQLiteOpenHelper {
    private final static String LOG_TAG ="SQLiteHelper_log";

    private final static String DATABASE_NAME ="apps_database";
    private final static int DATABASE_VERSION=1;
    public static final String APP_TABLE_NAME ="apps_table";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String APP_COLUMN_NAME = "name";
    public static final String APP_COLUMN_DESC = "description";
    public static final String APP_COLUMN_DEV = "developer";
    public static final String APP_COLUMN_IMG = "image";
    public static final String APP_COLUMN_INSTALLED = "installed";
    public static final String APP_COLUMN_EDITED = "edited";
    public static final String APP_COLUMN_UPDATED = "updated";
    public static final String APP_COLUMN_STATUS = "status";


    private static final String APP_CREATE_TABLE ="create table "+APP_TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            APP_COLUMN_NAME+" text not null,"+  // App name
            APP_COLUMN_DESC+ " text not null,"+ // App description
            APP_COLUMN_DEV+ " text not null,"+  // App developer name
            APP_COLUMN_IMG+ " int not null,"+   // App img, index of the picasso images array defined in models.ModelImage class
            APP_COLUMN_INSTALLED+ " text not null,"+  // Timestamp installation day
            APP_COLUMN_EDITED+ "  null,"+  // Timestamp edition day, if null the item was never edited
            APP_COLUMN_UPDATED+ " text  null,"+ // Timestamp updated day if is null means the app is non up to date
            APP_COLUMN_STATUS+" int default 0)"; // Flags Status to manage asynchronous behavior:  0 not doing nothing, 1 Installing, 2 Updating, 3 Des installing

    private static final String APP_DROP_TABLE ="drop table "+APP_TABLE_NAME;


    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(APP_CREATE_TABLE);

            Log.d(LOG_TAG,"Success  creating table for the first time");
        }catch (SQLException e){
            Log.d(LOG_TAG,"Error creating table for the first time : " + e);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // We are tasting, so we can drop and create all the tables in the database, losing all the data stored.
        // This has to change if we have distributed versions of the app
        try {
            db.execSQL(APP_DROP_TABLE);
            db.execSQL(APP_CREATE_TABLE);
            Log.d(LOG_TAG,"Success drooping and creating table");
        }catch (SQLException e ){
            Log.d(LOG_TAG,"Error drooping or creating table : " + e);
        }

    }
}
