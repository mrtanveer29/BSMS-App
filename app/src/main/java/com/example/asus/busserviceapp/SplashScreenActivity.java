package com.example.asus.busserviceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.busserviceapp.LoginActivity;
import com.example.asus.busserviceapp.R;

import java.util.Timer;


public class SplashScreenActivity extends Activity {
    final static int SPLASH_TIME_OUT = 2000;
    private boolean scheduled = false;
    private Timer timer;

    private Animation anim;
    private RelativeLayout rillay;
    private ImageView splashimg;
    private Handler handler;
    private ProgressBar progressBar;
    private TextView currentProgressView;
    private static int currentProgress=0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        rillay=(RelativeLayout) findViewById(R.id.lin_lay);

        splashimg = (ImageView) findViewById(R.id.splashview);
        handler=new Handler();

        // Typeface appTitleFont = Typeface.createFromAsset(getAssets(),"fonts/Impregnable.ttf");
        // splash_app_title.setTypeface(appTitleFont);
        StartAnimations();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    private void StartAnimations() {
//           Set R.anim.fade_out below to give fade out effect
//        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//        anim.reset();
//        rillay.clearAnimation();
//        rillay.startAnimation(anim);
//        splashimg.clearAnimation();
//        splashimg.startAnimation(anim);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(currentProgress<100){
                    currentProgress++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            progressBar.setProgress(currentProgress);
//                            currentProgressView.setText(currentProgress+"%");

                        }
                    });
                }
                if(currentProgress>=100){
//                    progressBar.setIndeterminate(true);
                    Intent home_page = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(home_page);
                    finish();

                }
            }
        }).start();
        timer = new Timer();
//        timer.schedule(new TimerTask(){
//            @Override
//            public void run() {
//                Intent home_page = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(home_page);
//                finish();
//            }}, SPLASH_TIME_OUT);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        overridePendingTransition(0,0);
        if (this.scheduled) {
            this.timer.cancel();
        }
        this.timer.purge();
    }

}
