package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Support;


public class DatabaseSupportList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_SUPPORT_FIRST_NAME = "first_name";
    private static final String COL_SUPPORT_LAST_NAME = "last_name";
    private static final String COL_SUPPORT_PASSWORD = "password";
    private static final String COL_SUPPORT_DATE = "created_at";
    private static final String COL_SUPPORT_IMAGE = "image";
    private static final String COL_SUPPORT_CODE = "support_code";



    public DatabaseSupportList(Context context) {
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



    public static void getData() {
        G.arrayListSupport.clear();

        Cursor cursor = G.database.rawQuery(" SELECT * " +
                " FROM Support ", null);

        while (cursor.moveToNext()) {
            Support data = new Support();

            data.setImage(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUPPORT_IMAGE)));
            data.setFirsName(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUPPORT_FIRST_NAME)));
            data.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUPPORT_LAST_NAME)));
            data.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUPPORT_DATE)));
            data.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUPPORT_PASSWORD)));
            data.setSupportCode(cursor.getInt(cursor.getColumnIndexOrThrow(COL_SUPPORT_CODE)));

            G.arrayListSupport.add(data);
        }

        cursor.close();

    }

}