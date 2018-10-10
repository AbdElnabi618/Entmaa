package com.kh618.entmaa.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kh618.entmaa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Register extends AppCompatActivity {
    String REGISTER_URL = "https://e3gaz.net/entmaa/public/api/v1/signup/en";

    EditText etUserName,etPassword,etConfigerPassword,etCompanyName
            ,etMobile,etEmail,etNakeName;
    ProgressBar registerProgress;

    RequestQueue requestQueue ;
    SharedPreferences sharedLoginStatus, sharedUserInformation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        //make notification transparent
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //get arabic link
        String language= Locale.getDefault().toString();
        if(language.equals("ar_EG") || language.equals("ar"))
            REGISTER_URL = "https://e3gaz.net/entmaa/public/api/v1/signin/ar";

        /// to make a responsive layout when keyboard appear
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        etUserName=findViewById(R.id.et_userName_register);
        etPassword=findViewById(R.id.et_Password_register);
        etConfigerPassword=findViewById(R.id.et_confirmPassword_register);
        etCompanyName=findViewById(R.id.et_companyName_register);
        etMobile=findViewById(R.id.et_mobileNumber_register);
        etEmail=findViewById(R.id.et_email_register);
        etNakeName=findViewById(R.id.et_nakedName_register);

        sharedLoginStatus = this.getSharedPreferences(
                getResources().getString(R.string.status_key),MODE_PRIVATE);
        sharedUserInformation  = this.getSharedPreferences(
                getResources().getString(R.string.userInformation),MODE_PRIVATE);

        registerProgress=findViewById(R.id.register_progress);

        requestQueue = Volley.newRequestQueue(this);

    }
    public void register(View v){
        if(isConnectToInterner()) {
        registerProgress.setVisibility(View.VISIBLE);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JRegisterData =new JSONObject(response);
                            if(JRegisterData.getString("value").equals("true")){
                                JSONObject dataObject = JRegisterData.getJSONObject("data");

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

                                Intent i =new Intent(Register.this,Home.class);
                                registerProgress.setVisibility(View.INVISIBLE);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }else {
                                registerProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register.this,
                                        JRegisterData.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            registerProgress.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registerProgress.setVisibility(View.INVISIBLE);
                        Log.e("register",error.toString());
                    }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> map = new HashMap<>();

                map.put("password",etPassword.getText().toString());
                map.put("username",etUserName.getText().toString());
                map.put("mobile",etMobile.getText().toString());
                map.put("email",etEmail.getText().toString());
                map.put("name",etNakeName.getText().toString());
                map.put("company_name",etCompanyName.getText().toString());

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }else{
        Toast.makeText(this, "please check your internet connection", Toast.LENGTH_SHORT).show();
    }
    }

    public void finish(View view) {
        finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  boolean isConnectToInterner() {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (Exception e) {
                Log.e("internet error1", "Error: ", e);
            }
        } else {
            Log.d("internet error2", "No network present");
        }
        return false;
    }
}
