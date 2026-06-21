package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.User;


public class DatabaseUserList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String TABLE_NAME_USER = "User";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_IMAGE = "img";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";

    public DatabaseUserList(Context context) {
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
        G.arrayListUser.clear();
        Cursor cursor = G.database.rawQuery("SELECT * FROM " + TABLE_NAME_USER + " ORDER BY "+COL_USER_ID +" ASC", null);

        while (cursor.moveToNext()) {
            User data = new User();
            data.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_USER_ID)));
            data.setFirsName(cursor.getString(cursor.getColumnIndexOrThrow(COL_FIRST_NAME)));
            data.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COL_LAST_NAME)));
            data.setImage(cursor.getString(cursor.getColumnIndexOrThrow(COL_IMAGE)));
            data.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
            data.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE)));

            G.arrayListUser.add(data);
        }
        cursor.close();

//    for (int i = 0; i < G.arrayListUser.size(); i++) {
//      Log.i("FSR", "getData: "+G.arrayListUser.get(i).getUserId() + " "+G.arrayListUser.get(i).getFirsName()+ " "+G.arrayListUser.get(i).getLastName()+ " "+G.arrayListUser.get(i).getImage()
//      + " " + G.arrayListUser.get(i).getEmail());
//    }

    }

}