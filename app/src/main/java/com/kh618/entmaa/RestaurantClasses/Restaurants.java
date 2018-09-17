package com.kh618.entmaa.RestaurantClasses;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.kh618.entmaa.Adabter.RestaurantAdapter;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

public class Restaurants extends Activity {

    RecyclerView recyclerView ;
    RestaurantAdapter adapter;
    ArrayList<RestaurantListItem> list;
    ImageView backRow;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        drawerLayout =findViewById(R.id.drawer_Restaurant);
        navigationView = findViewById(R.id.navigation_Restaurant);
        new MyNavigation(this,drawerLayout,navigationView);

        backRow= findViewById(R.id.backrow);

        list = new ArrayList<>();
        list.add(new RestaurantListItem(R.mipmap.resturants1,getResources().getString(R.string.elSabahy),getResources().getString(R.string.offer)));
        list.add(new RestaurantListItem(R.mipmap.resturants2,getResources().getString(R.string.elMohamady),getResources().getString(R.string.offer)));
        list.add(new RestaurantListItem(R.mipmap.resturants3,getResources().getString(R.string.frenshFraiz),null));
        list.add(new RestaurantListItem(R.mipmap.resturants4,getResources().getString(R.string.mrFish),null));

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new RestaurantAdapter(this,list);

        recyclerView.setAdapter(adapter);



        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar")){
            backRow.setImageResource(R.mipmap.arrow);
        }

    }
    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

}
