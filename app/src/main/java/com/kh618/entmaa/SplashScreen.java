package com.kh618.entmaa;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.jaeger.library.StatusBarUtil;

public class SplashScreen extends AppCompatActivity {

    Handler h;
    ProgressBar progressBar;

    int setProgress=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StatusBarUtil.setTransparent(this);

        progressBar = findViewById(R.id.progress);

         h = new Handler();
         h.postDelayed(run,50);


    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            if(setProgress <=100) {
                progressBar.setProgress(setProgress);
                setProgress++;
                h.postDelayed(run,50);
            }else{
                Intent i = new Intent(SplashScreen.this,Login.class);
                startActivity(i);
            }
        }
    };

}
