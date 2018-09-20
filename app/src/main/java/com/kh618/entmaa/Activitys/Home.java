package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.kh618.entmaa.Adapter.HomeAdapter;
import com.kh618.entmaa.MyClasses.ListItem;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;
import com.kh618.entmaa.RestaurantClasses.Restaurants;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView;
    ArrayList<ListItem> arrayList;
    HomeAdapter adapter;
    private final int columnsNum = 2;

    ArrayList<Integer> imagesSlider;

    TextView recycleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mDrawerLayout = findViewById(R.id.drawer_home);
        navigationView = findViewById(R.id.navigation_home);
        new MyNavigation(this, mDrawerLayout, navigationView);

        recycleCount= findViewById(R.id.recycle_itemCount);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, columnsNum));

        arrayList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            arrayList.add(new ListItem(R.mipmap.health_beauty, getResources().getString(R.string.health)));
            arrayList.add(new ListItem(R.mipmap.hops, getResources().getString(R.string.restaurants)));
            arrayList.add(new ListItem(R.mipmap.hops, getResources().getString(R.string.shops)));
            arrayList.add(new ListItem(R.mipmap.hotels, getResources().getString(R.string.hotels)));
        }

        adapter = new HomeAdapter(this, arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recycleCount.setText(getResources().getString(R.string.firstWord_numItem)+" "+String.valueOf(adapter.getItemCount())+" "+
                getResources().getString(R.string.secondWord_numItem));

        imagesSlider = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            imagesSlider.add(R.mipmap.bg_resturants);

        SliderLayout s = findViewById(R.id.slider);
        for (int i = 0; i < imagesSlider.size(); i++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(imagesSlider.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            s.addSlider(defaultSliderView);
        }

//        mDrawerLayout.addDrawerListener(new ActionBarDrawerToggle(this, mDrawerLayout
//                , null, 0, 0) {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                mSlideState = false;//is Closed
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                mSlideState = true;//is Opened
//            }
//        });

    }

    public void openIntent(View v) {
        Intent i = new Intent(Home.this, Restaurants.class);
        startActivity(i);
    }

    public void openDrawer(View v) {
        mDrawerLayout.openDrawer(Gravity.START);
    }

}

