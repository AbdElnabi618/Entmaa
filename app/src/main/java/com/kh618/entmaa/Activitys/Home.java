package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.kh618.entmaa.Adapter.HomeAdapter;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.Interfaces.LocalApi;
import com.kh618.entmaa.MyClasses.BannersItem;
import com.kh618.entmaa.MyClasses.ListItem;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;
import com.kh618.entmaa.RestaurantClasses.Restaurants;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {


    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView;
    ArrayList<ListItem.Item> arrayList;
    HomeAdapter adapter;

    Retrofit retrofit;
    Api api;

    ProgressBar loadingBanner;

    ArrayList<BannersItem.Items> imagesSlider;
    SliderLayout s;

    TextView recycleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        retrofit = new Retrofit.Builder().baseUrl(api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        imagesSlider = new ArrayList<>();
        loadingBanner=findViewById(R.id.loading_banner);


        mDrawerLayout = findViewById(R.id.drawer_home);
        navigationView = findViewById(R.id.navigation_home);
        new MyNavigation(this, mDrawerLayout, navigationView);

        recycleCount = findViewById(R.id.recycle_itemCount);
        recyclerView = findViewById(R.id.recycleView);
        int columnsNum = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, columnsNum));

        arrayList = new ArrayList<>();


        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        s = findViewById(R.id.slider);
        showBanners();
        showCategories();
    }

    public void showCategories(){
        Call<ListItem> item ;
        if(Locale.getDefault().toString().equals("ar_EG")||Locale.getDefault().toString().equals("ar"))
            item= api.getCategoriesAr();
        else
        item= api.getCategoriesEn();

        item.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(@NonNull Call<ListItem> call, @NonNull Response<ListItem> response) {
                ListItem list = response.body();
                if(list != null &&list.getValue()) {
                    arrayList.addAll(list.getData());
                    adapter = new HomeAdapter(Home.this, arrayList);
                    recyclerView.setAdapter(adapter);
                    recycleCount.setText(getString(R.string.firstWord_numItem) + " " +
                            String.valueOf(list.getCount()) + " " +getString(R.string.secondWord_numItem));
                }else{
                    Toast.makeText(Home.this, "error in loading items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListItem> call, @NonNull Throwable t) {
                Toast.makeText(Home.this, t.getMessage()+"error 18", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showBanners() {
        loadingBanner.setVisibility(View.VISIBLE);

        Call<BannersItem> listCall ;
        if(Locale.getDefault().toString().equals("ar_EG") || Locale.getDefault().toString().equals("ar"))
        listCall = api.getBannersAr();
        else
            listCall= api.getBannersEn();

        listCall.enqueue(new Callback<BannersItem>() {
            @Override
            public void onResponse(@NonNull Call<BannersItem> call, @NonNull Response<BannersItem> response) {
                assert response.body() != null;
                if(response.body().getValue()) {
                    loadingBanner.setVisibility(View.INVISIBLE);
                    imagesSlider.addAll(response.body().getData());
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        TextSliderView defaultSliderView = new TextSliderView(getApplicationContext());
                        // initialize a SliderLayout
                        defaultSliderView
                                .image(imagesSlider.get(i).getImage())
                                .setScaleType(BaseSliderView.ScaleType.Fit).description(imagesSlider.get(i).getText());
                        s.addSlider(defaultSliderView);
                    }
                }
                else{
                    Toast.makeText(Home.this, "fail,please check internet our connect us", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BannersItem> call, @NonNull Throwable t) {
                Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openIntent(View v) {
        Intent i = new Intent(Home.this, Restaurants.class);
        startActivity(i);
    }

    public void openDrawer(View v) {
        mDrawerLayout.openDrawer(Gravity.START);
    }

}

