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

import com.kh618.entmaa.Adapter.WhoUsAdapter;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.WhoUsItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

public class WhoUs extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_us);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView backRow = findViewById(R.id.backrow);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_WhoUs);
        navigationView = findViewById(R.id.navigation_WhoUs);
        new MyNavigation(this,drawerLayout,navigationView);

        ArrayList<WhoUsItem> items = new ArrayList<>();

        for(int i =0; i<3;i++)
        items.add(new WhoUsItem(getResources().getString(R.string.terms),getResources().getString(R.string.autoText)));

        RecyclerView recyclerView = findViewById(R.id.whoUs_recycle);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        WhoUsAdapter adapter = new WhoUsAdapter(this,items);

        recyclerView.setAdapter(adapter);

    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }
}
