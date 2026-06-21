package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Request;


public class DatabaseRequestList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_REQUEST_DATE = "request_date";
    private static final String COL_STORE_NAME = "store_name";
    private static final String COL_REQUEST_DESCRIPTION = "description";


    public DatabaseRequestList(Context context) {
        super(context, G.DB_DIR + G.DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
        }

        if (newVersion == 3) {
        }
    }



    public static void getData(int userId) {
        G.arrayListRequest.clear();

        Cursor cursor = G.database.rawQuery("SELECT Seller_Request.submitted_at AS request_date, Seller_Request.store_name, Seller_Request.description " +
                " FROM Seller_Request " +
                " WHERE Seller_Request.user_id = " + userId +
                " UNION ALL " +
                " SELECT Collabration_Request.created_at AS request_date, Store.store_name, Collabration_Request.message " +
                " FROM Collabration_Request " +
                " JOIN Store ON Store.store_id = Collabration_Request.store_id " +
                " WHERE Collabration_Request.user_id = " + userId +
                " ORDER BY request_date ASC", null);


        while (cursor.moveToNext()) {
            Request data = new Request();

            data.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COL_REQUEST_DATE)));
            data.setStoreName(cursor.getString(cursor.getColumnIndexOrThrow(COL_STORE_NAME)));
            data.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COL_REQUEST_DESCRIPTION)));


            G.arrayListRequest.add(data);
        }

        cursor.close();

//    for (int i = 0; i < G.arrayListRequest.size(); i++) {
//      Log.i("FSR", "getData: "+G.arrayListRequest.get(i).getStoreName() + " " + G.arrayListRequest.get(i).getDate() + " " +G.arrayListRequest.get(i).getDescretiption());
//    }


    }
}