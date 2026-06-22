package me.farinaz.saeedi.ranjbar83.myunibazaar.frameworkstartup.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;
import me.farinaz.saeedi.ranjbar83.myunibazaar.framework.core.G;

public class ActivitySplash extends AppCompatActivity {
    public ImageView imageLogo;
    public TextView txtNameApp;
    public LinearLayout cvRecommend;

    public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private static final int DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cv_recommend), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageLogo=findViewById(R.id.img_logo);
        txtNameApp=findViewById(R.id.textView);
        cvRecommend=findViewById(R.id.cv_recommend);
        txtNameApp.setTypeface(G.fontTitle);


        showAlphaAnimation();
        txtAnim(DURATION);

        cvRecommend.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cvRecommend.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                animFill();
            }
        });

        G.copyDatabaseIfNeeded();
        introActivity();

    }


    private void txtAnim(int duration){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtNameApp.setAlpha(0f);
                txtNameApp.setVisibility(View.VISIBLE);

                txtNameApp.animate()
                        .alpha(1f)
                        .setDuration(1000)
                        .setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator())
                        .start();
            }
        },duration);
    }



    private void showAlphaAnimation() {


        imageLogo.setTranslationX(-screenWidth);
        imageLogo.setVisibility(View.VISIBLE);

        imageLogo.post(new Runnable() {
            @Override
            public void run() {

                float finalX = (screenWidth / 2f) - (imageLogo.getWidth() / 2f);

                ObjectAnimator moveAnim = ObjectAnimator.ofFloat(
                        imageLogo,
                        "translationX",
                        -screenWidth,
                        finalX
                );

                moveAnim.setDuration(DURATION);
                moveAnim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());

                moveAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {}

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        AnimatorSet scaleSet = new AnimatorSet();

                        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(imageLogo, "scaleX", 1f, 1.2f);
                        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(imageLogo, "scaleY", 1f, 1.2f);

                        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imageLogo, "scaleX", 1.2f, 1f);
                        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imageLogo, "scaleY", 1.2f, 1f);

                        scaleSet.play(scaleUpX).with(scaleUpY);
                        scaleSet.play(scaleDownX).with(scaleDownY).after(scaleUpX);

                        scaleSet.setDuration(300);
                        scaleSet.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {}

                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                });

                moveAnim.start();
            }
        });
    }

    private void animFill(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cvRecommend != null && cvRecommend.getWidth() > 0 && cvRecommend.getHeight() > 0) {
                    Animator anim = ViewAnimationUtils.createCircularReveal(cvRecommend, screenWidth/2, screenHeight/2, 0, screenHeight);
                    cvRecommend.setBackgroundColor(getResources().getColor(R.color.background));
                    anim.setDuration(2000);
                    anim.start();
                }
            }
        }, DURATION+1000);

    }

    private void introActivity() {
        Handler btn_handler = new Handler();
        btn_handler.postDelayed(new Runnable() {

            public void run() {
                Intent intentIntroduction = new Intent(ActivitySplash.this, ActivityMain.class);
                ActivitySplash.this.startActivity(intentIntroduction);
                finish();

            }

        }, 4500);
    }


}