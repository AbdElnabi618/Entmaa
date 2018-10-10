package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh618.entmaa.Adapter.QuestionsAdapter;
import com.kh618.entmaa.Adapter.RepliesAdapter;
import com.kh618.entmaa.Interfaces.Api;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.MyClasses.RepliedItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Replies extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView question;
    EditText addComment;

    RecyclerView recyclerView;
    RepliesAdapter adapter;

    int quizeId=0,userid;
    String local,quize;

    Retrofit retrofit;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replies);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ImageView backRow = findViewById(R.id.backrow);

        question=findViewById(R.id.question_answered);
        addComment= findViewById(R.id.addComment);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

         quizeId =  getIntent().getExtras().getInt("id",-1);
         quize =  getIntent().getExtras().getString("question",null);

         question.setText(quize);

         retrofit= new Retrofit.Builder().baseUrl(Api.BaseUrlLink).
                 addConverterFactory(GsonConverterFactory.create())
                 .build();
         api = retrofit.create(Api.class);

         local = Locale.getDefault().toString();
        if (local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        SharedPreferences userData = getSharedPreferences(getString(R.string.userInformation),MODE_PRIVATE);
        userid=Integer.parseInt(userData.getString(getString(R.string.userId_key),"-1)"));

        drawerLayout = findViewById(R.id.drawer_replies);
        navigationView = findViewById(R.id.navigation_replies);
        new MyNavigation(this,drawerLayout,navigationView);

        recyclerView= findViewById(R.id.answerRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getAllAnswers();
    }

    public void getAllAnswers(){
        Call<RepliedItem> call;
        if (local.equals("ar_EG") || local.equals("ar"))
            call= api.getAnswers("answers/"+quizeId+"/ar");
        else
            call= api.getAnswers("answers/"+quizeId+"/en");

        call.enqueue(new Callback<RepliedItem>() {
            @Override
            public void onResponse(Call<RepliedItem> call, Response<RepliedItem> response) {

                RepliedItem repliedItem =response.body();
                if(repliedItem !=null && repliedItem.getValue()){
                    adapter= new RepliesAdapter(getApplicationContext(),repliedItem.getData());
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(Replies.this, "error 14", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RepliedItem> call, Throwable t) {
                Toast.makeText(Replies.this, "error 15:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

    public void pushComment(View v){
        Call<JsonObject> call ;
        if(Locale.getDefault().toString().equals("ar_EG") ||Locale.getDefault().toString().equals("ar"))
            call=api.postAnswerAr(userid,quizeId,addComment.getText().toString());
        else
            call=api.postAnswerEn(userid,quizeId,addComment.getText().toString());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject value=response.body();

                assert value != null;
                if((value.get("value").toString()).equals("true")){
                    Toast.makeText(Replies.this, "thank you", Toast.LENGTH_SHORT).show();
                    addComment.setText("");
                    getAllAnswers();

                }else{
                    Toast.makeText(Replies.this, "your answer not submit , plz try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(Replies.this, "error 16: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
