package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.TransitionRes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
public class G extends Application {
    public static Typeface font1, font2, font3, fontRequest, fontTitle, fontNumber, fontNumberPrice, titleAdapter, fontNumberPercent;

    private static Context context;
    private static Activity currentActivity;
    private static LayoutInflater layoutInflater;
    private static TransitionInflater transitionInflater;
    private static Handler handler;
    private static DisplayMetrics displayMetrics;
    private static G app;

    public static SQLiteDatabase database;
    public static  String DB_DIR ;
    public static  String DATABASE_NAME = "uni_bazaar.db";
    public static  String DB_PATH = "";

    public static ArrayList<User> arrayListUser = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        context = getApplicationContext();
        handler = new Handler();
        displayMetrics = getResources().getDisplayMetrics();
        layoutInflater = LayoutInflater.from(context);
        transitionInflater = TransitionInflater.from(context);

        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        fontSet();
    }

    private void fontSet() {

        // Fonts
        font1 = Typeface.createFromAsset(G.getContext().getAssets(),getString(R.string.font1) );
        font2 = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.font2));
        font3 = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.font3));
        fontRequest = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.fontRequest));
        fontNumber = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.fontNumber));
        titleAdapter = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.titleAdapter));
        fontTitle = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.fontTitle));
        fontNumberPrice = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.fontNumberPrice));
        fontNumberPercent = Typeface.createFromAsset(G.getContext().getAssets(), getString(R.string.fontNumberPersent));
    }

    public static Context getContext(){
        if(currentActivity != null){
            return currentActivity;
        }
        return context;
    }
    public static G get(){
        return app;
    }

    public static void setCurrentActivity(Activity activity){
        currentActivity = activity;
    }
    public static Activity getCurrentActivity(){
        return currentActivity;
    }
    public static LayoutInflater getLayoutInflater(){
        return layoutInflater;
    }
    public static TransitionInflater gettransitionInflater(){
        return transitionInflater;
    }
    public static View layoutInflate(@LayoutRes int res){
        return layoutInflater.inflate(res, null);
    }
    public static View layoutInflate(@LayoutRes int res, @Nullable ViewGroup root){
        return layoutInflater.inflate(res, root);
    }
    public static Transition transitionInflate(@TransitionRes int res){
        return transitionInflater.inflateTransition(res);
    }
    public static Handler getHandler(){
        return handler;
    }
    public  static DisplayMetrics getDisplayMetrics(){
        return displayMetrics;
    }

    public static void copyDatabaseIfNeeded() {
        try {
            boolean result = createDataBase();

            if (result) {
                Log.d(TAG, "Database copied successfully");
            } else {
                Log.d(TAG, "Database already exists");
            }

            openDataBase();

        } catch (IOException e) {
            Log.e(TAG, "ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static boolean createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database already exists: " + DB_PATH + DATABASE_NAME);
            return false;
        } else {
            File dbDir = new File(DB_PATH);
            if (!dbDir.exists()) {
                boolean created = dbDir.mkdirs();
                Log.d(TAG, "Database folder was created " + created);
                if (!created) {
                    throw new IOException("Can not created database folder");
                }
            }

            copyDataBase();
            return true;
        }
    }
    public static boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        boolean exists = dbFile.exists();
        Log.d(TAG, "Check database in " + DB_PATH + DATABASE_NAME + ": " + exists);


        if (exists) {
            long fileSize = dbFile.length();
            Log.d(TAG, "Size of db file: " + fileSize + "byte.");
        }

        return exists;
    }
    public static void copyDataBase() throws IOException {
        InputStream myInput = null;
        OutputStream myOutput = null;

        try {
            myInput = context.getAssets().open(DATABASE_NAME);

            String outFileName = DB_PATH + DATABASE_NAME;
            myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            int totalRead = 0;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
                totalRead += length;
            }

            Log.d(TAG, "Copy completed: " + totalRead);

        } catch (IOException e) {
            Log.e(TAG, "ERROR: " + e.getMessage());
            throw e;
        } finally {
            try {
                if (myOutput != null) {
                    myOutput.flush();
                    myOutput.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "ERROR: " + e.getMessage());
            }

            try {
                if (myInput != null) {
                    myInput.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "ERROR: " + e.getMessage());
            }
        }
    }
    public static SQLiteDatabase openDataBase() {
        try {
            String myPath = DB_PATH + DATABASE_NAME;
            File dbFile = new File(myPath);

            if (!dbFile.exists()) {
                Log.e(TAG, "db file not exist " + myPath);
                return null;
            }

            database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            Log.d(TAG, "Database open successfully");
            return database;
        } catch (Exception e) {
            Log.e(TAG, "ERROR: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
