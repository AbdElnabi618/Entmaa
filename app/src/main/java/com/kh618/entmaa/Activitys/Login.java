package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.kh618.entmaa.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);
    }

    public void NewAccount(View v){
        Intent i =new Intent(Login.this,Register.class);
        startActivity(i);
    }
    public void ForgetPassword(View v){
        //TODO Forget Password
    }

    public void Login(View view) {
        Intent i =new Intent(Login.this,Test.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onDestroy();
    }
}
