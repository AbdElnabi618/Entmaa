package com.kh618.entmaa.Activitys;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kh618.entmaa.Adapter.PackageAdapter;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.PackageItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

public class Packages extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ArrayList<PackageItem> list;
    RecyclerView recyclerView;
    PackageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView backRow = findViewById(R.id.backrow);

        drawerLayout = findViewById(R.id.drawer_package);
        navigationView = findViewById(R.id.navigation_package);
        new MyNavigation(this,drawerLayout,navigationView);

        list=new ArrayList<>();

        recyclerView =findViewById(R.id.package_recycle);

        adapter =new PackageAdapter(this,list);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar")) {
            backRow.setImageResource(R.mipmap.arrow);
            list.add(new PackageItem(R.mipmap.c1,R.mipmap.c4,
                    getResources().getString(R.string.monthlyPackage),"200,00"));
            list.add(new PackageItem(R.mipmap.c2,R.mipmap.c5,
                    getResources().getString(R.string.halfYearPackage),"700,00"));
            list.add(new PackageItem(R.mipmap.c3,R.mipmap.c6,
                    getResources().getString(R.string.YearPackage),"1000,00"));
        }else{
            list.add(new PackageItem(R.mipmap.c1_en,R.mipmap.c4,
                    getResources().getString(R.string.monthlyPackage),"200,00"));
            list.add(new PackageItem(R.mipmap.c2_en,R.mipmap.c5,
                    getResources().getString(R.string.halfYearPackage),"700,00"));
            list.add(new PackageItem(R.mipmap.c3_en,R.mipmap.c6,
                    getResources().getString(R.string.YearPackage),"1000,00"));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }
}
