package com.kh618.entmaa.RestaurantClasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.Locale;

public class RestaurantDeitales extends Activity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_deitalse);

        drawerLayout =findViewById(R.id.drawer_detailes);
        navigationView = findViewById(R.id.navigation_detailes);
        new MyNavigation(this,drawerLayout,navigationView);

        ImageView backrow = findViewById(R.id.backrow);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar")){
            backrow.setImageResource(R.mipmap.arrow);
        }

    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }
}
