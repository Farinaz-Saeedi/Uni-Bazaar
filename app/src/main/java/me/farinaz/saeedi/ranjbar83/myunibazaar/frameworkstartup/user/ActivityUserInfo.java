package me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper.DrawableResourceHelper;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.discount.ActivityDiscountCode;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.product.ActivityBestProduct;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.product.ActivityProduct;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.product.ActivityRecommended;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.request.ActivityRequest;
import me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.store.ActivityStore;

public class ActivityUserInfo extends AppCompatActivity {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;

    private ImageView imgInfo;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtPhone;
    private TextView txtDiscount;
    private TextView txtRequest;
    private TextView txtView;
    private TextView txtSeller;
    private TextView txtStore;
    private TextView txtRecomm;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private CardView cardDiscountCode;
    private CardView cardRequest;
    private CardView cardViewed;
    private CardView cardSellers;
    private CardView cardStore;
    private CardView cardRecomm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cv_recommend), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgInfo = findViewById(R.id.img_info);
        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPhone = findViewById(R.id.txt_phone);
        txtDiscount = findViewById(R.id.txt_user_discount);
        txtRequest = findViewById(R.id.txt_user_request);
        txtView = findViewById(R.id.txt_user_viewed);
        txtSeller = findViewById(R.id.txt_user_sellers);
        txtStore = findViewById(R.id.txt_user_store);
        txtRecomm = findViewById(R.id.txt_user_recomm);
        txt1 = findViewById(R.id.txt_info_1);
        txt2 = findViewById(R.id.txt_info_2);
        txt3 = findViewById(R.id.txt_info_3);
        cardDiscountCode = findViewById(R.id.card_discount_code);
        cardRequest = findViewById(R.id.card_request);
        cardViewed = findViewById(R.id.card_viewed);
        cardSellers = findViewById(R.id.card_sellers);
        cardStore = findViewById(R.id.card_store);
        cardRecomm = findViewById(R.id.card_recomm);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (intent.hasExtra("id")){
            id = bundle.getInt("id");
        }
        if (intent.hasExtra("email")){
            email = bundle.getString("email");
        }
        if (intent.hasExtra("image")){
            image = bundle.getString("image");
        }
        if (intent.hasExtra("phone")){
            phone = bundle.getString("phone");
        }
        if (intent.hasExtra("firstName")){
            firstName = bundle.getString("firstName");
        }
        if (intent.hasExtra("lastName")){
            lastName = bundle.getString("lastName");
        }

        txtDiscount.setTypeface(G.fontTitle);
        txtRecomm.setTypeface(G.fontTitle);
        txtRequest.setTypeface(G.fontTitle);
        txtSeller.setTypeface(G.fontTitle);
        txtStore.setTypeface(G.fontTitle);
        txtView.setTypeface(G.fontTitle);

        txt1.setTypeface(G.font2);
        txt2.setTypeface(G.font2);
        txt3.setTypeface(G.font2);
        txtName.setTypeface(G.font1);
        txtEmail.setTypeface(G.font1);
        txtPhone.setTypeface(G.font1);

        txtName.setText(firstName + " " + lastName);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        imgInfo.setImageDrawable(DrawableResourceHelper.getDrawableByName(G.getContext(), image));

        Toolbar toolbar = findViewById(R.id.toolbar_user_info);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("My Account");
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cardDiscountCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityDiscountCode.class);
                intentIntroduction.putExtra("id", id);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });
        cardRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityRequest.class);
                intentIntroduction.putExtra("id", id);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });

        cardViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityProduct.class);
                intentIntroduction.putExtra("id", id);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });

        cardSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityBestProduct.class);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });

        cardRecomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityRecommended.class);
                intentIntroduction.putExtra("id", id);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });

        cardStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntroduction = new Intent(ActivityUserInfo.this, ActivityStore.class);
                intentIntroduction.putExtra("id", id);
                ActivityUserInfo.this.startActivity(intentIntroduction);
            }
        });
    }
}