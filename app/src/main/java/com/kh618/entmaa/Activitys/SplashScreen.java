package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.jaeger.library.StatusBarUtil;
import com.kh618.entmaa.R;

public class SplashScreen extends AppCompatActivity {

    Handler h;
    ProgressBar progressBar;

    int setProgress=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

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

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
