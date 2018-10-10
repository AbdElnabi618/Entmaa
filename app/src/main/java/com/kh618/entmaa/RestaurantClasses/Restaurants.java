package com.kh618.entmaa.RestaurantClasses;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kh618.entmaa.Adapter.RestaurantAdapter;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.RestaurantListItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Restaurants extends Activity {

    RecyclerView recyclerView ;
    RestaurantAdapter adapter;
    ArrayList<RestaurantListItem.Item> list;
    ImageView backRow;
    Retrofit retrofit;
    Api api;
    int comingId;
    ProgressBar rest_loading;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        retrofit = new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        rest_loading= findViewById(R.id.rest_loading);

        drawerLayout =findViewById(R.id.drawer_Restaurant);
        navigationView = findViewById(R.id.navigation_Restaurant);
        new MyNavigation(this,drawerLayout,navigationView);

        backRow= findViewById(R.id.backrow);

        list = new ArrayList<>();

        comingId= getIntent().getExtras().getInt("id",0);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar")){
            backRow.setImageResource(R.mipmap.arrow);
        }
        getShopsData();

    }

    public void getShopsData(){
        rest_loading.setVisibility(View.VISIBLE);
        Call<RestaurantListItem> call ;
        if(Locale.getDefault().toString().equals("ar_EG") || Locale.getDefault().toString().equals("ar"))
            call=api.getShopsData("shops/"+comingId+"/ar");
        else
            call=api.getShopsData("shops/"+comingId+"/en");

        call.enqueue(new Callback<RestaurantListItem>() {
            @Override
            public void onResponse(Call<RestaurantListItem> call, Response<RestaurantListItem> response) {
                rest_loading.setVisibility(View.INVISIBLE);
                RestaurantListItem listItem = response.body();
                if(listItem != null &&listItem.getValue()){
                    list.addAll(listItem.getData());
                    adapter = new RestaurantAdapter(getApplicationContext(),list);
                    recyclerView.setAdapter(adapter);
                    if (adapter.getItemCount() ==0)
                        Toast.makeText(Restaurants.this, "no data to show, coming soon", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Restaurants.this, "list has no data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RestaurantListItem> call, Throwable t) {
                Toast.makeText(Restaurants.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

}
