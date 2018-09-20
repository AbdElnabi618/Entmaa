package com.kh618.entmaa.Activitys;

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
import android.widget.ImageView;

import com.kh618.entmaa.Adapter.NotificationAdapter;
import com.kh618.entmaa.Adapter.RepliesAdapter;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.NotificationsItem;
import com.kh618.entmaa.MyClasses.RepliedItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

public class Notifications extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView;
    NotificationAdapter adapter;
    ArrayList<NotificationsItem> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageView backRow = findViewById(R.id.backrow);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        list=new ArrayList<>();
        for (int i=0;i<10;i++)
            list.add(new NotificationsItem(getResources().getString(R.string.userName_notifi),
                    getResources().getString(R.string.notification_content)));

        String local = Locale.getDefault().toString();
        if (local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_notification);
        navigationView = findViewById(R.id.navigation_notification);
        new MyNavigation(this,drawerLayout,navigationView);

        recyclerView= findViewById(R.id.notificationRecycle);
        adapter=new NotificationAdapter(this,list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }
}
