package com.kh618.entmaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);
    }
}
