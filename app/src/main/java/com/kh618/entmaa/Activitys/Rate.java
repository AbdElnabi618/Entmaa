package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Rate extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView imgGood,imgExcellent,imgBad;
    String rate="";
    EditText etComment;
    int userid, shopid;

    Retrofit retrofit;
    Api api;
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

        imgGood = findViewById(R.id.rate_good);
        imgExcellent = findViewById(R.id.rate_excellent);
        imgBad = findViewById(R.id.rate_bad);
        etComment = findViewById(R.id.rate_comment);

        retrofit = new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        SharedPreferences userData = getSharedPreferences(getString(R.string.userInformation),MODE_PRIVATE);
        userid=Integer.parseInt(userData.getString(getString(R.string.userId_key),"-1)"));

        SharedPreferences shopData = getSharedPreferences(getString(R.string.shopData),MODE_PRIVATE);
        shopid=shopData.getInt(getString(R.string.shopId),-1);

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
            rate=((TextView)findViewById(R.id.excellent_tv)).getText().toString();
        }else if(v.getId()==R.id.good_tv ||v.getId()==R.id.rate_good){
            imgGood.setImageResource(R.mipmap.good);
            imgBad.setImageResource(R.mipmap.bad);
            imgExcellent.setImageResource(R.mipmap.excellent);
            rate=((TextView)findViewById(R.id.good_tv)).getText().toString();
        }else if(v.getId()==R.id.bad_tv ||v.getId()==R.id.rate_bad){
            imgExcellent.setImageResource(R.mipmap.excellent);
            imgGood.setImageResource(R.mipmap.good2);
            imgBad.setImageResource(R.mipmap.bad2);
            rate=((TextView)findViewById(R.id.bad_tv)).getText().toString();
        }
    }
    public void submitRating(View v){
        Call<JsonObject> call ;
        if(Locale.getDefault().toString().equals("ar_EG") ||Locale.getDefault().toString().equals("ar"))
            call=api.postRateAr(rate,userid,shopid,etComment.getText().toString());
        else
            call=api.postRateEn(rate,userid,shopid,etComment.getText().toString());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject value=response.body();

                assert value != null;
                if((value.get("value").toString()).equals("true")){
                    Toast.makeText(Rate.this, "thank you", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Rate.this,Home.class));
                }else{
                    Toast.makeText(Rate.this, "your rate not submit , plz try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(Rate.this, "error 10: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
