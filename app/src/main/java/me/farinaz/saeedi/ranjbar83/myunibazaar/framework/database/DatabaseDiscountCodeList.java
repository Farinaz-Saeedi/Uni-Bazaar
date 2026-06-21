package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.DiscountCode;


public class DatabaseDiscountCodeList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_CODE_ID = "code_id";
    private static final String COL_CODE_SCOPE = "scope";
    private static final String COL_CODE_AMOUNT = "discount_amount";


    public DatabaseDiscountCodeList(Context context) {
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




    public static void getCodeInfo(int userId) {
        G.arrayListDiscountCode.clear();
        Cursor cursor = G.database.rawQuery("SELECT " +
                " Discount_Code.code_id, COALESCE(Percentage_Code.percent, Fixed_Code.value) AS discount_amount, " +
                " Discount_Code.scope " +
                " FROM Discount_Code " +
                " LEFT JOIN Percentage_Code ON Discount_Code.code_id = Percentage_Code.code_id " +
                " LEFT JOIN Fixed_Code ON Discount_Code.code_id = Fixed_Code.code_id " +
                " WHERE " +
                " Discount_Code.is_public = TRUE OR EXISTS ( " +
                " SELECT 1 " +
                " FROM User_Discount_Code " +
                " WHERE User_Discount_Code.code_id = Discount_Code.code_id AND User_Discount_Code.user_id =  "+userId +
                " ) " +
                " UNION ALL " +
                " SELECT " +
                " 0 AS code_id, " +
                " '7%' AS discount_amount, " +
                " 'All Stores' AS scope " +
                " FROM VIP_User " +
                " WHERE " +
                " VIP_User.user_id =  "+userId +
                " AND NOT EXISTS ( " +
                " SELECT 1 " +
                " FROM Discount_Code " +
                " WHERE Discount_Code.scope = 'all_booths' AND Discount_Code.producer_id IS NULL " +
                " ) " , null);


        while (cursor.moveToNext()) {
            DiscountCode data = new DiscountCode();
            data.setCodId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_CODE_ID)));
            data.setScope(cursor.getString(cursor.getColumnIndexOrThrow(COL_CODE_SCOPE)));
            data.setAmount(cursor.getString(cursor.getColumnIndexOrThrow(COL_CODE_AMOUNT)));

            G.arrayListDiscountCode.add(data);
        }

        cursor.close();

//    for (int i = 0; i < G.arrayListDiscountCode.size(); i++) {
//      Log.i("FSR", "getData: "+G.arrayListDiscountCode.get(i).getCodId() + " "+G.arrayListDiscountCode.get(i).getScope()+ " "+G.arrayListDiscountCode.get(i).getAmount());
//    }

    }
}