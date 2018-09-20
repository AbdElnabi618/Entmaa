package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.Locale;


public class Rate extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView imgGood,imgExcellent,imgBad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        /// to make a responsive layout when keyboard appear
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ImageView backRow = findViewById(R.id.backrow);

        imgGood= findViewById(R.id.rate_good);
        imgExcellent= findViewById(R.id.rate_excellent);
        imgBad= findViewById(R.id.rate_bad);


        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_rate);
        navigationView = findViewById(R.id.navigation_rate);
        new MyNavigation(this,drawerLayout,navigationView);
    }
    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

    public void statuse(View v){
        if(v.getId()==R.id.excellent_tv ||v.getId()==R.id.rate_excellent){
            imgExcellent.setImageResource(R.mipmap.excellent2);
            imgGood.setImageResource(R.mipmap.good2);
            imgBad.setImageResource(R.mipmap.bad);
        }else if(v.getId()==R.id.good_tv ||v.getId()==R.id.rate_good){
            imgGood.setImageResource(R.mipmap.good);
            imgBad.setImageResource(R.mipmap.bad);
            imgExcellent.setImageResource(R.mipmap.excellent);
        }else if(v.getId()==R.id.bad_tv ||v.getId()==R.id.rate_bad){
            imgExcellent.setImageResource(R.mipmap.excellent);
            imgGood.setImageResource(R.mipmap.good2);
            imgBad.setImageResource(R.mipmap.bad2);
        }
    }
    public void openIntent(View v){
        Intent i = new Intent(Rate.this,Home.class);
        startActivity(i);
    }
}
