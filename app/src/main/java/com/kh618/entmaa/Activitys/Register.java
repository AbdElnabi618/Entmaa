package com.kh618.entmaa.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.kh618.entmaa.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        StatusBarUtil.setTransparent(this);
    }
    public void register(View v){

    }

    public void finish(View view) {
        finish();
    }
}
