package me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.support;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.database.DatabaseSupportOperationList;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper.DrawableResourceHelper;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.widget.CircularProgressBar;

public class ActivitySupportInfo extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String password;
    private String date;
    private String image;
    private int code;

    private ImageView imgSupportInfo;
    private TextView txtSupportName;
    private TextView txtSupportPassword;
    private TextView txtSupportDate;

    private CircularProgressBar pb1;
    private CircularProgressBar pb2;
    private CircularProgressBar pb3;
    private CircularProgressBar pb4;

    private TextView txtPercent1;
    private TextView txtPercent2;
    private TextView txtPercent3;
    private TextView txtPercent4;

    private TextView txtTitle1;
    private TextView txtTitle2;
    private TextView txtTitle3;
    private TextView txtTitle4;

    private TextView txtSupportInfo1;
    private TextView txtSupportInfo2;
    private TextView txtSupportInfo3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.tb_support);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgSupportInfo = findViewById(R.id.img_support_info);
        txtSupportName = findViewById(R.id.txt_support_name);
        txtSupportPassword = findViewById(R.id.txt_support_password);
        txtSupportDate = findViewById(R.id.txt_support_date);

        txtSupportName.setTypeface(G.font1);
        txtSupportPassword.setTypeface(G.font1);
        txtSupportDate.setTypeface(G.font1);


        pb1 = findViewById(R.id.pb_1);
        pb2 = findViewById(R.id.pb_2);
        pb3 = findViewById(R.id.pb_3);
        pb4 = findViewById(R.id.pb_4);

        txtPercent1 = findViewById(R.id.txt_percent1);
        txtPercent2 = findViewById(R.id.txt_percent2);
        txtPercent3 = findViewById(R.id.txt_percent3);
        txtPercent4 = findViewById(R.id.txt_percent4);

        txtTitle1 = findViewById(R.id.txt_operation);
        txtTitle2 = findViewById(R.id.txt_booths);
        txtTitle3 = findViewById(R.id.txt_badge);
        txtTitle4 = findViewById(R.id.txt_code);

        txtTitle1.setTypeface(G.font2);
        txtTitle2.setTypeface(G.font2);
        txtTitle3.setTypeface(G.font2);
        txtTitle4.setTypeface(G.font2);

        txtPercent1.setTypeface(G.font2);
        txtPercent2.setTypeface(G.font2);
        txtPercent3.setTypeface(G.font2);
        txtPercent4.setTypeface(G.font2);

        txtSupportInfo1 = findViewById(R.id.txt_support_info_1);
        txtSupportInfo2 = findViewById(R.id.txt_support_info_2);
        txtSupportInfo3 = findViewById(R.id.txt_support_info_3);

        txtSupportInfo1.setTypeface(G.font2);
        txtSupportInfo2.setTypeface(G.font2);
        txtSupportInfo3.setTypeface(G.font2);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (intent.hasExtra("password")){
            password = bundle.getString("password");
        }
        if (intent.hasExtra("image")){
            image = bundle.getString("image");
        }
        if (intent.hasExtra("date")){
            date = bundle.getString("date");
        }
        if (intent.hasExtra("firstName")){
            firstName = bundle.getString("firstName");
        }
        if (intent.hasExtra("lastName")){
            lastName = bundle.getString("lastName");
        }
        if (intent.hasExtra("code")){
            code = bundle.getInt("code");
        }

        txtSupportName.setText(firstName + " " + lastName);
        Log.i("FSR", "name***** " + firstName + " " + lastName);
        txtSupportDate.setText(date);
        txtSupportPassword.setText(password);
        imgSupportInfo.setImageDrawable(DrawableResourceHelper.getDrawableByName(G.getContext(), image));


        long animationDuration = 2500;


        pb1.setProgressWithAnimation(DatabaseSupportOperationList.getOperationPercent(code), animationDuration);
        pb2.setProgressWithAnimation(DatabaseSupportOperationList.getStorePercent(code), animationDuration);
        pb3.setProgressWithAnimation(DatabaseSupportOperationList.getBadgePercent(code), animationDuration);
        pb4.setProgressWithAnimation(DatabaseSupportOperationList.getCodePercent(code), animationDuration);

        txtPercent1.setText((int) DatabaseSupportOperationList.getOperationPercent(code)+"%");
        txtPercent2.setText((int)DatabaseSupportOperationList.getStorePercent(code)+"%");
        txtPercent3.setText((int)DatabaseSupportOperationList.getBadgePercent(code)+"%");
        txtPercent4.setText((int)DatabaseSupportOperationList.getCodePercent(code)+"%");
    }
}