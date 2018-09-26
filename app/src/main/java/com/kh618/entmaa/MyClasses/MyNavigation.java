package com.kh618.entmaa.MyClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.kh618.entmaa.Activitys.BankAccounts;
import com.kh618.entmaa.Activitys.ContectUs;
import com.kh618.entmaa.Activitys.Home;
import com.kh618.entmaa.Activitys.Login;
import com.kh618.entmaa.Activitys.Notifications;
import com.kh618.entmaa.Activitys.Packages;
import com.kh618.entmaa.Activitys.TheQuestions;
import com.kh618.entmaa.R;
import com.kh618.entmaa.Activitys.WhoUs;
import com.kh618.entmaa.Activitys.Setting;

import static android.content.Context.MODE_PRIVATE;

public class MyNavigation {
   private Activity activity;
   private DrawerLayout drawerLayout;
   private boolean mStatus;
   private NavigationView navigationView;
   private SharedPreferences sharedPreferences;
    private SharedPreferences sharedUserInformation;

    public MyNavigation(Activity activity, DrawerLayout drawerLayout, NavigationView navigationView) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;
        this.navigationView = navigationView;
        mStatus = false;
        sharedPreferences=activity.getSharedPreferences(activity.getString(R.string.status_key), MODE_PRIVATE);
        sharedUserInformation  = activity.getSharedPreferences(
                activity.getString(R.string.userInformation),MODE_PRIVATE);
        CreateDrawer();
    }

    public void CreateDrawer(){
        drawerLayout.addDrawerListener(new ActionBarDrawerToggle(activity,drawerLayout,
                null,0,0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mStatus = false;//is Closed
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mStatus = true;//is Opened
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                OpenFreg(item.getItemId());
                drawerLayout.closeDrawers();
                mStatus=false;
                return false;
            }
        });
    }
    public void OpenFreg(int id) {

        if (id == R.id.home_item) {
        OpenActivity(Home.class);
        } else if (id == R.id.askMember_item) {
            OpenActivity(TheQuestions.class);
        } else if (id == R.id.contact_item) {
            OpenActivity(ContectUs.class);
        } else if (id == R.id.logout_item) {
            sharedPreferences.edit().putString(activity.getString(R.string.login_key),"false").apply();
            SharedPreferences.Editor informationEditor = sharedUserInformation.edit();

            informationEditor.putString(activity.getString(R.string.user_name_key),"");
            informationEditor.putString(activity.getString(R.string.userId_key),"");
            informationEditor.putString(activity.getString(R.string.email_key),"");
            informationEditor.putString(activity.getString(R.string.phone_key),"");
            informationEditor.putString(activity.getString(R.string.company_name_key),"");
            informationEditor.putString(activity.getString(R.string.naked_name_key),"");
            informationEditor.apply();
                OpenActivity(Login.class);
        } else if (id == R.id.notification_item) {
            OpenActivity(Notifications.class);
        } else if (id == R.id.packages_item) {
            OpenActivity(Packages.class);
        } else if (id == R.id.setting_item) {
            OpenActivity(Setting.class);
        }else if (id == R.id.whoUs_item) {
            OpenActivity(WhoUs.class);
        }else if (id == R.id.banks_item) {
        OpenActivity(BankAccounts.class);
        }
    }
    public void OpenActivity(Class c){
        if(activity.getClass() != c) {
            Intent i = new Intent(activity, c);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

}
