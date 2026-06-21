package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Store;


public class DatabaseStoreList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_STORE_NAME = "store_name";
    private static final String COL_STORE_ID = "store_id";
    private static final String COL_STORE_IMAGE = "image";
    private static final String COL_STORE_TYPE = "ownership_type";
    private static final String COL_OWNER_FIRST_NAME = "first_name";
    private static final String COL_OWNER_LAST_NAME = "last_name";



    public DatabaseStoreList(Context context) {
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


    public static void getStoreInfo(int userId) {
        G.arrayListStore.clear();
        Cursor cursor = G.database.rawQuery(" SELECT DISTINCT " +
                " s.store_name, " +
                " s.image, " +
                " s.store_id, "+
                " u.first_name, " +
                " u.last_name, " +
                " se.ownership_type " +
                " FROM " +
                " User_Avtivity ua " +
                " JOIN " +
                " Store s ON ua.store_id = s.store_id " +
                " JOIN " +
                " User u ON s.user_id = u.user_id " +
                " JOIN " +
                " Seller se ON s.user_id = se.user_id " +
                " WHERE " +
                " ua.user_id = " + userId +
                " AND ua.activity_type = 'purchase' ", null);

        while (cursor.moveToNext()) {
            Store data = new Store();

            data.setIdStore(cursor.getInt(cursor.getColumnIndexOrThrow(COL_STORE_ID)));
            data.setName(cursor.getString(cursor.getColumnIndexOrThrow(COL_STORE_NAME)));
            data.setImage(cursor.getString(cursor.getColumnIndexOrThrow(COL_STORE_IMAGE)));
            data.setType(cursor.getString(cursor.getColumnIndexOrThrow(COL_STORE_TYPE)));
            data.setOwnerFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COL_OWNER_FIRST_NAME)));
            data.setOwnerLastName(cursor.getString(cursor.getColumnIndexOrThrow(COL_OWNER_LAST_NAME)));
            data.setOwnerLastName(cursor.getString(cursor.getColumnIndexOrThrow(COL_OWNER_LAST_NAME)));
            data.setFraudulent(getIsFraudulent(cursor.getInt(cursor.getColumnIndexOrThrow(COL_STORE_ID))));

            G.arrayListStore.add(data);
        }
        cursor.close();
    }

    private static Boolean getIsFraudulent(int storeId){
        Cursor cursor = G.database.rawQuery(" SELECT  COUNT(*) AS res" +
                " FROM Stall_Badge" +
                " WHERE Stall_Badge.store_id = " + storeId +
                " AND Stall_Badge.name = 'Fraudster'", null);


        int badgeCount = 0;
        if (cursor != null && cursor.moveToFirst()) {
            badgeCount = cursor.getInt(cursor.getColumnIndexOrThrow("res"));
            cursor.close();
        }

        return badgeCount >= 2;
    }

}