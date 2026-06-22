package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.Product;


public class DatabaseProductList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_PRODUCT_NAME = "title";
    private static final String COL_PRODUCT_PRICE = "price";
    private static final String COL_PRODUCT_IMAGE = "image";
    private static final String COL_PRODUCT_DESCRIPTION = "description";



    public DatabaseProductList(Context context) {
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


    public static void getViewedProducts(int userId) {
        G.arrayListProduct.clear();

        Cursor cursor = G.database.rawQuery("SELECT Product.title, Product.price, Product.image, Product.description " +
                " FROM User_Avtivity " +
                " JOIN Product ON User_Avtivity.product_id = Product.product_id " +
                " WHERE User_Avtivity.user_id = " + userId +
                " AND User_Avtivity.activity_type = 'product_view' " +
                " LIMIT 10 ", null);


        fillDataArrayList(cursor);


    }

    public static void getBestSellingProducts() {
        G.arrayListProduct.clear();

        Cursor cursor = G.database.rawQuery("SELECT Product.title, Product.price, Product.image, Product.description " +
                " FROM User_Avtivity " +
                " JOIN Product ON User_Avtivity.product_id = Product.product_id " +
                " WHERE User_Avtivity.activity_type = 'purchase' "  +
                " GROUP BY " +
                " User_Avtivity.product_id" +
                " ORDER BY" +
                " COUNT(User_Avtivity.product_id) DESC " +
                " LIMIT 10 ", null);


        fillDataArrayList(cursor);

    }

    public static void getRecommendedProducts(int userId) {
        G.arrayListProduct.clear();

        Cursor cursor = G.database.rawQuery("WITH UserPurchases AS (" +
                " SELECT user_id, product_id" +
                " FROM User_Avtivity " +
                " WHERE activity_type = 'purchase' )," +
                " TargetUserPurchases AS ( " +
                " SELECT product_id" +
                " FROM UserPurchases" +
                " WHERE user_id = " + userId + " ), " +
                " SimilarUsers AS ( " +
                " SELECT up.user_id, COUNT(up.product_id) AS common_purchases_count " +
                " FROM UserPurchases AS up " +
                " JOIN TargetUserPurchases AS tup ON up.product_id = tup.product_id " +
                " WHERE up.user_id != " + userId +
                " GROUP BY up.user_id " +
                " ORDER BY common_purchases_count DESC " +
                " LIMIT 5 ), " +
                " RecommendedProducts AS ( " +
                " SELECT up.product_id, " +
                " COUNT(up.product_id) AS purchase_frequency " +
                " FROM " +
                " UserPurchases AS up " +
                " JOIN SimilarUsers AS su ON up.user_id = su.user_id " +
                " WHERE up.product_id NOT IN (SELECT product_id FROM TargetUserPurchases) " +
                " GROUP BY up.product_id " +
                " ORDER BY purchase_frequency DESC )" +
                " SELECT p.title, p.price , p.image , p.description" +
                " FROM RecommendedProducts AS rp " +
                " JOIN Product AS p ON rp.product_id = p.product_id " +
                " ORDER BY " +
                " rp.purchase_frequency DESC " +
                " LIMIT 10 ", null);


        fillDataArrayList(cursor);

    }
    private static void fillDataArrayList(Cursor cursor){
        while (cursor.moveToNext()) {
            Product data = new Product();

            data.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_NAME)));
            data.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_PRICE)));
            data.setDis(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_DESCRIPTION)));
            data.setImage(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_IMAGE)));

            G.arrayListProduct.add(data);
        }
        cursor.close();
    }

}