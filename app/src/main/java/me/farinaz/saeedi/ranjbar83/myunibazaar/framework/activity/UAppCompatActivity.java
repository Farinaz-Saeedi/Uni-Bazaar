package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;



public class UAppCompatActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        G.setCurrentActivity(this);
    }
}
