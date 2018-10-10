package com.kh618.entmaa.RestaurantClasses;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kh618.entmaa.Adapter.ImagesAdapter;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.Activitys.BarCodeScanner;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.ShopDeitals;
import com.kh618.entmaa.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantDeitales extends Activity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //member var
    ImageView bigImage/*, image1,image2,image3,image4*/,barCode;
    TextView name,note,offer,date;

    Api api;
    Retrofit retrofit;
    String local;
    RecyclerView recyclerView;
    ImagesAdapter adapter;
    ProgressBar shop_loading;

    int comingId,shopId=-1;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_deitalse);

        comingId=getIntent().getExtras().getInt("id",0);

        drawerLayout =findViewById(R.id.drawer_detailes);
        navigationView = findViewById(R.id.navigation_detailes);
        new MyNavigation(this,drawerLayout,navigationView);

        shop_loading= findViewById(R.id.shop_loading);
        bigImage=findViewById(R.id.big_image);
        recyclerView=findViewById(R.id.shop_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false));

        barCode=findViewById(R.id.barcode);

        sharedPreferences=this.getSharedPreferences(getString(R.string.shopData),MODE_PRIVATE);

        name=findViewById(R.id.restaurantName);
        note=findViewById(R.id.information);
        offer=findViewById(R.id.newOffer);
        date=findViewById(R.id.date);

        retrofit=new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        api=retrofit.create(Api.class);

        ImageView backrow = findViewById(R.id.backrow);

       local= Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar")){
            backrow.setImageResource(R.mipmap.arrow);
        }
        getdata();
    }

    public void getdata(){
        shop_loading.setVisibility(View.VISIBLE);
        Call<ShopDeitals> call ;
        if(local.equals("ar_EG") || local.equals("ar"))
            call=api.getShopData("shop/"+comingId+"/ar");
        else
            call=api.getShopData("shop/"+comingId+"/en");

        call.enqueue(new Callback<ShopDeitals>() {
            @Override
            public void onResponse(Call<ShopDeitals> call, Response<ShopDeitals> response) {
                shop_loading.setVisibility(View.INVISIBLE);
                ShopDeitals deitals = response.body();
                if(deitals!=null && deitals.getValue()) {
                    ShopDeitals.Data data =deitals.getData();

                    Picasso.with(getApplicationContext()).load(data.getImage()).into(bigImage);
                    adapter= new ImagesAdapter(getApplicationContext(),data.getImages());

                    recyclerView.setAdapter(adapter);

                    shopId=data.getId();

                    name.setText(data.getName());
                    note.setText(data.getNote());
                    if(!data.getOffer().equals(""))
                        offer.setText(data.getOffer());
                    else
                        offer.setText(getString(R.string.noOffer));
                    date.setText(data.getDate());
                }
            }

            @Override
            public void onFailure(Call<ShopDeitals> call, Throwable t) {
                Toast.makeText(RestaurantDeitales.this, "error in connection no. 15", Toast.LENGTH_SHORT).show();
                shop_loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

    public void OpenBarCodeScanner(View v){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.shopId),shopId).apply();
        startActivity(new Intent(RestaurantDeitales.this,BarCodeScanner.class));
    }
}
