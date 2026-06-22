package me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database.DatabaseSupportList;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database.DatabaseUserList;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.support.ActivitySupport;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.user.ActivityUser;

public class ActivityMain extends AppCompatActivity {

    private CardView cvExit;
    private CardView cvAbout;
    private CardView cvUser;
    private CardView cvSupport;
    private TextView txtUser;
    private TextView txtSupport;
    private TextView txtAbout;
    private TextView txtExit;
    private TextView txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cv_recommend), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cvUser = findViewById(R.id.card_user);
        cvAbout = findViewById(R.id.card_about);
        cvExit = findViewById(R.id.card_exit);
        cvSupport = findViewById(R.id.card_support);
        txtUser = findViewById(R.id.txt_user_id);
        txtSupport = findViewById(R.id.txt_support_id);
        txtAbout = findViewById(R.id.txt_about_id);
        txtExit = findViewById(R.id.txt_exit);
        txtList = findViewById(R.id.txt_list);

        txtUser.setTypeface(G.fontTitle);
        txtSupport.setTypeface(G.fontTitle);
        txtAbout.setTypeface(G.fontTitle);
        txtExit.setTypeface(G.fontTitle);
        txtList.setTypeface(G.font1);

        cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUserList.getData();
                Intent intentIntroduction = new Intent(ActivityMain.this, ActivityUser.class);
                ActivityMain.this.startActivity(intentIntroduction);

            }
        });
        cvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSupportList.getData();
                Intent intentIntroduction = new Intent(ActivityMain.this, ActivitySupport.class);
                ActivityMain.this.startActivity(intentIntroduction);

            }
        });
        cvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityMain.this, ActivityAbout.class);
                ActivityMain.this.startActivity(intentIntroduction);

            }
        });
        cvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}