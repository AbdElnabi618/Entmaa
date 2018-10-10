package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;
import android.widget.Toast;

import com.kh618.entmaa.Adapter.QuestionsAdapter;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheQuestions extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView addQuestion;

    RecyclerView recyclerView;
    QuestionsAdapter adapter;

    Retrofit retrofit;
    Api api;
    String local ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_questions);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView backRow = findViewById(R.id.backrow);
        addQuestion =findViewById(R.id.addQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TheQuestions.this,AskQuiz.class);
                startActivity(i);
            }
        });

        retrofit = new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        api= retrofit.create(Api.class);


         local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_question);
        navigationView = findViewById(R.id.navigation_question);
        new MyNavigation(this,drawerLayout,navigationView);


        recyclerView= findViewById(R.id.questionRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        getAllQuestions();
    }

    public void getAllQuestions(){
        Call<QuizeItem> call ;
        if(local.equals("ar_EG") || local.equals("ar"))
            call=api.getPostsAr();
        else
            call=api.getPostsEn();

        call.enqueue(new Callback<QuizeItem>() {
            @Override
            public void onResponse(Call<QuizeItem> call, Response<QuizeItem> response) {
                QuizeItem quizeItem= response.body();
                if(quizeItem!= null && quizeItem.getValue()){
                    adapter = new QuestionsAdapter(getApplicationContext(),quizeItem.getData());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<QuizeItem> call, Throwable t) {
                Toast.makeText(TheQuestions.this, "error 13 :"+t.getMessage(), Toast.LENGTH_SHORT).show();
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
