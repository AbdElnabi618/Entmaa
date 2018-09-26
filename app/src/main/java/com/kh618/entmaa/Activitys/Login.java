package com.kh618.entmaa.Activitys;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaeger.library.StatusBarUtil;
import com.kh618.entmaa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Login extends AppCompatActivity {

        EditText et_userName, et_password;
        ProgressBar loginProgress;
        String userName , password;
        RequestQueue requestQueue;
        private  String LOGIN_URL="https://e3gaz.net/entmaa/public/api/v1/signin/en";
       SharedPreferences sharedLoginStatus, sharedUserInformation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        String language=Locale.getDefault().toString();

        if(language.equals("ar_EG") || language.equals("ar"))
            LOGIN_URL = "https://e3gaz.net/entmaa/public/api/v1/signin/ar";

        et_userName=findViewById(R.id.userName_login);
        et_password=findViewById(R.id.password_login);

        loginProgress= findViewById(R.id.login_progress);

        sharedLoginStatus = this.getSharedPreferences(
                getResources().getString(R.string.status_key),MODE_PRIVATE);
        sharedUserInformation  = this.getSharedPreferences(
                getResources().getString(R.string.userInformation),MODE_PRIVATE);

         requestQueue = Volley.newRequestQueue(this);
    }

    public void NewAccount(View v){
        Intent i =new Intent(Login.this,Register.class);
        startActivity(i);
    }
    public void ForgetPassword(View v){
        //TODO Forget Password
    }

    public void Login(View view) {
        userName= et_userName.getText().toString();
        password = et_password.getText().toString();

        loginProgress.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    if (responseObject.getString("value").equals("true")){

                        JSONObject dataObject = responseObject.getJSONObject("data");

                        SharedPreferences.Editor informationEditor = sharedUserInformation.edit();

                        informationEditor.putString(getString(R.string.user_name_key),dataObject.getString("username"));
                        informationEditor.putString(getString(R.string.userId_key),dataObject.getString("id"));
                        informationEditor.putString(getString(R.string.email_key),dataObject.getString("email"));
                        informationEditor.putString(getString(R.string.phone_key),dataObject.getString("mobile"));
                        informationEditor.putString(getString(R.string.company_name_key),dataObject.getString("company_name"));
                        informationEditor.putString(getString(R.string.naked_name_key),dataObject.getString("name"));
                        informationEditor.apply();

                        SharedPreferences.Editor loginStatusEditor= sharedLoginStatus.edit();

                        loginStatusEditor.putString(getString(R.string.login_key),"true");
                        loginStatusEditor.apply();

                        et_userName.setText("");
                        et_password.setText("");

                        Intent i =new Intent(Login.this,Home.class);
                        loginProgress.setVisibility(View.INVISIBLE);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }else{
                        loginProgress.setVisibility(View.INVISIBLE);

                        et_userName.setText("");
                        et_password.setText("");

                        Toast.makeText(Login.this, responseObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    et_userName.setText("");
                    et_password.setText("");

                    loginProgress.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("login",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> par = new HashMap<>();
                par.put("username",userName);
                par.put("password",password);
                return par;
            }
        };
        requestQueue.add(stringRequest);


    }

}
