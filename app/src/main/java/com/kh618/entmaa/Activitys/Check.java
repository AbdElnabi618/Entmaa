package com.kh618.entmaa.Activitys;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.Locale;

public class Check extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        ImageView backRow = findViewById(R.id.backrow);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_Check);
        navigationView = findViewById(R.id.navigation_Check);
        new MyNavigation(this,drawerLayout,navigationView);
    }
    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }
}