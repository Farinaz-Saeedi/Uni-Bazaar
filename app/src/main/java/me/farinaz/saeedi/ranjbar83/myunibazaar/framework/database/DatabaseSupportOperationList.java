package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct.SupportOperation;


public class DatabaseSupportOperationList extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    private static final String COL_SUPPORT_OPERATION_PERCENT = "total_operations_percentage";
    private static final String COL_SUPPORT_STORE_PERCENT = "total_stores_percentage";
    private static final String COL_SUPPORT_CODE_PERCENT = "total_codes_percentage";
    private static final String COL_SUPPORT_BADGE_PERCENT = "total_badges_percentage";


    public DatabaseSupportOperationList(Context context) {
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


    public static float getOperationPercent(int supportCode) {
        G.arrayListSupportOperation.clear();

        Cursor cursor = G.database.rawQuery(" WITH SellerRequests AS ( " +
                " SELECT COUNT(request_no) AS reviewed_requests_count " +
                " FROM Seller_Request " +
                " WHERE support_code = " + supportCode + "), " +
                " StallBadges AS ( " +
                " SELECT COUNT(badge_no) AS awarded_badges_count " +
                " FROM Stall_Badge " +
                " WHERE support_code = " + supportCode + " ), " +
                " SupportProducer AS ( " +
                " SELECT porducer_id " +
                " FROM Support " +
                " WHERE support_code = " + supportCode + " )," +
                " DiscountCodes AS ( " +
                " SELECT COUNT(code_id) AS generated_discount_codes_count " +
                " FROM Discount_Code " +
                " WHERE producer_id = (SELECT porducer_id FROM SupportProducer) " + " ), " +
                " TotalOperations AS ( " +
                " SELECT " +
                " (SR.reviewed_requests_count + SB.awarded_badges_count + DC.generated_discount_codes_count) AS total_operations_count " +
                " FROM " +
                " SellerRequests AS SR, " +
                " StallBadges AS SB, " +
                " DiscountCodes AS DC " + " ) " +
                " SELECT " +
                " ROUND(100.0 * T.total_operations_count / 15.0, 2) AS total_operations_percentage " +
                " FROM " +
                " TotalOperations AS T ", null);

        float operationPercent = 0.0f;

        while (cursor.moveToNext()) {
            SupportOperation data = new SupportOperation();

            float percent = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_SUPPORT_OPERATION_PERCENT));
            data.setSupportPercentOperation(percent);
            G.arrayListSupportOperation.add(data);

            operationPercent = percent;
        }

        cursor.close();
        return operationPercent;
    }

    public static float getStorePercent(int supportCode) {
        G.arrayListSupportOperation.clear();

        Cursor cursor = G.database.rawQuery(" SELECT " +
                " ROUND(100.0 * total_stores / 8, 2) AS total_stores_percentage " +
                " FROM ( " +
                " SELECT COUNT(*) AS total_stores " +
                " FROM Seller_Request " +
                " WHERE support_code = " + supportCode +
                " AND rejection_reason IS NULL " +
                " )   ", null);

        float storePercent = 0.0f;

        while (cursor.moveToNext()) {
            SupportOperation data = new SupportOperation();

            float percent = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_SUPPORT_STORE_PERCENT));
            data.setSupportPercentStore(percent);
            G.arrayListSupportOperation.add(data);
            storePercent = percent;
        }

        cursor.close();
        return storePercent;
    }

    public static float getCodePercent(int supportCode) {
        G.arrayListSupportOperation.clear();

        Cursor cursor = G.database.rawQuery(
                " SELECT ROUND(100.0 * total_codes / 10, 2) AS total_codes_percentage " +
                        " FROM ( " +
                        " SELECT COUNT(*) AS total_codes " +
                        " FROM Discount_Code " +
                        " JOIN Support ON Support.porducer_id = Discount_Code.producer_id " +
                        " WHERE Support.support_code =  " + supportCode +
                        " ) ", null);

        float codePercent = 0.0f;

        while (cursor.moveToNext()) {
            SupportOperation data = new SupportOperation();

            float percent = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_SUPPORT_CODE_PERCENT));
            data.setSupportPercentDiscount(percent);
            G.arrayListSupportOperation.add(data);
            codePercent = percent;
        }

        cursor.close();
        return codePercent;
    }

    public static float getBadgePercent(int supportCode) {
        G.arrayListSupportOperation.clear();

        Cursor cursor = G.database.rawQuery(
                " SELECT ROUND(100.0 * total_bage / 19, 2) AS total_badges_percentage " +
                        " FROM ( " +
                        " SELECT COUNT(*) AS total_bage " +
                        " FROM Stall_Badge " +
                        " WHERE Stall_Badge.support_code = " + supportCode +
                        " ) ", null);

        float badgePercent = 0.0f;
        while (cursor.moveToNext()) {
            SupportOperation data = new SupportOperation();

            float percent = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_SUPPORT_BADGE_PERCENT));
            data.setSupportPercentBadge(percent);
            G.arrayListSupportOperation.add(data);
            badgePercent = percent;
        }

        cursor.close();
        return badgePercent;
    }

    public static void func (int supportCode) {
        G.arrayListSupportOperation.clear();

        Cursor cursor1 = G.database.rawQuery(" SELECT AVG(julianday(T2.start_date) - julianday(T1.reviewed_at)) " +
                " FROM Seller_Request AS T1 " +
                " INNER JOIN Stall_Badge AS T2 ON T1.store_name = T2.s_name " +
                " WHERE T1.rejection_reason IS NULL AND T1.support_code = " + supportCode + " AND T2.name = 'Fraudster' " +
                " ORDER BY T2.start_date ASC " +
                " LIMIT 1 OFFSET 1  ", null);

        Cursor cursor2 = G.database.rawQuery(" WITH VerifiedStalls AS ( " +
                " SELECT DISTINCT sr.store_name " +
                " FROM Seller_Request sr " +
                " WHERE sr.support_code = " + supportCode +
                " AND sr.rejection_reason IS NULL " +
                " ), " +
                " FraudsterStalls AS ( " +
                " SELECT DISTINCT sb.s_name " +
                " FROM Stall_Badge sb " +
                " JOIN VerifiedStalls vs ON sb.s_name = vs.store_name " +
                " WHERE sb.name = 'Fraudster' " +
                ") " +
                " SELECT " +
                "    CASE " +
                "        WHEN (SELECT COUNT(*) FROM VerifiedStalls) = 0 THEN 0.0 " +
                "        WHEN (SELECT COUNT(*) FROM FraudsterStalls) <= 50 THEN 0.0 " +
                "        WHEN (SELECT COUNT(*) FROM FraudsterStalls) = (SELECT COUNT(*) FROM VerifiedStalls) THEN 100.0 " +
                "        ELSE " +
                "            ROUND( " +
                "                100.0 * (SELECT COUNT(*) FROM FraudsterStalls) " +
                "                / " +
                "                (SELECT COUNT(*) FROM VerifiedStalls) " +
                "            , 2) " +
                "    END AS fraud_percentage " , null);

        Cursor cursor3 = G.database.rawQuery(" WITH RankedStores AS ( " +
                " SELECT " +
                " S.store_name, " +
                " COUNT(UA.user_id) AS purchase_count, " +
                " DENSE_RANK() OVER (ORDER BY COUNT(UA.user_id) DESC) AS store_rank " +
                " FROM " +
                " User_Avtivity AS UA " +
                " JOIN " +
                " Store AS S " +
                " ON " +
                " UA.store_id = S.store_id " +
                " WHERE " +
                " UA.activity_type = 'purchase' " +
                " GROUP BY " +
                " S.store_name " +
                " ) " +
                " SELECT " +
                " RankedStores.store_name, " +
                " RankedStores.purchase_count, " +
                " RankedStores.store_rank " +
                " FROM " +
                " RankedStores JOIN Seller_Request ON Seller_Request.store_name = RankedStores.store_name " +
                " WHERE Seller_Request.support_code = " + supportCode + " AND Seller_Request.rejection_reason IS NULL " +
                " ORDER BY " +
                " store_rank ASC; ", null);


        Cursor cursor5 = G.database.rawQuery(" WITH support_producer AS ( " +
                " SELECT porducer_id " +
                " FROM Support " +
                " WHERE support_code = " + supportCode +
                " ), " +
                " codes AS ( " +
                " SELECT " +
                " dc.code_id, " +
                " fc.value AS discount_amount " +
                " FROM Discount_Code dc " +
                " JOIN Fixed_Code fc ON dc.code_id = fc.code_id " +
                " WHERE dc.producer_id = (SELECT producer_id FROM support_producer) " +
                " ), " +
                " order_discounts AS ( " +
                " SELECT " +
                " o.user_id, " +
                " c.discount_amount " +
                " FROM Order AS o " +
                " JOIN codes c ON o.code_id = c.code_id " +
                " ) " +
                " user_total AS ( " +
                " SELECT " +
                " user_id, " +
                " SUM(discount_amount) AS user_discount_sum " +
                " FROM order_discounts " +
                " GROUP BY user_id " +
                " ), " +
                " total_sum AS (\n" +
                " SELECT SUM(user_discount_sum) AS total_discount\n" +
                " FROM user_total\n" +
                " ) " +
                " SELECT " +
                " user_id, " +
                " user_discount_sum, " +
                " ROUND( (user_discount_sum * 100.0) / total_discount , 2 ) AS share_percentage " +
                " FROM user_total, total_sum " +
                " ORDER BY user_discount_sum DESC " +
                " LIMIT 1 ", null);

    }

}