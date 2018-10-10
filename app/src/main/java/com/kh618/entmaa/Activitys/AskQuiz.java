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

public class AskQuiz extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText etQuize;
    String local ;

    Retrofit retrofit;
    Api api;
    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_quiz);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView backRow = findViewById(R.id.backrow);
        etQuize=findViewById(R.id.add_quiz);

        retrofit= new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        api=retrofit.create(Api.class);

        SharedPreferences userData = getSharedPreferences(getString(R.string.userInformation),MODE_PRIVATE);
        userid=Integer.parseInt(userData.getString(getString(R.string.userId_key),"-1)"));

        local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_askQuiz);
        navigationView = findViewById(R.id.navigation_askQuiz);
        new MyNavigation(this,drawerLayout,navigationView);
    }
    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

    public void SubmitQuestion(View v){
        Call<JsonObject> call ;
        if(local.equals("ar_EG") ||local.equals("ar"))
            call=api.postQuizeAr(userid,etQuize.getText().toString());
        else
            call=api.postQuizeEn(userid,etQuize.getText().toString());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject value=response.body();

                assert value != null;
                if((value.get("value").toString()).equals("true")){
                    Toast.makeText(AskQuiz.this, "thank you , your question send", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AskQuiz.this,Home.class));
                }else{
                    Toast.makeText(AskQuiz.this, "your question not send , plz try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(AskQuiz.this, "error 11: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
