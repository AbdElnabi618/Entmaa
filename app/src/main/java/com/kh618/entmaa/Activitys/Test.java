package com.kh618.entmaa.Activitys;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.Locale;

public class Test extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DrawerLayout drawerLayout = findViewById(R.id.mydrawer);
        NavigationView navigationView = findViewById(R.id.navigationView);
        MyNavigation navigation = new MyNavigation(this,drawerLayout,navigationView);

        TextView t = findViewById(R.id.local);
        t.setText(Locale.getDefault().toString());
    }


}
