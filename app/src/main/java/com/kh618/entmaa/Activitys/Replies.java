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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kh618.entmaa.Adapter.QuestionsAdapter;
import com.kh618.entmaa.Adapter.RepliesAdapter;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.MyClasses.RepliedItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;
import java.util.Locale;

public class Replies extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView question;
    EditText addComment;

    RecyclerView recyclerView;
    RepliesAdapter adapter;
    ArrayList<RepliedItem> list ;
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

        String s = (String) getIntent().getExtras().get("question");
        question.setText(s);

        String local = Locale.getDefault().toString();
        if (local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add(new RepliedItem(getResources().getString(R.string.answer1)));
            list.add(new RepliedItem(getResources().getString(R.string.answer2)));
        }

        drawerLayout = findViewById(R.id.drawer_replies);
        navigationView = findViewById(R.id.navigation_replies);
        new MyNavigation(this,drawerLayout,navigationView);

        recyclerView= findViewById(R.id.answerRecycle);
        adapter=new RepliesAdapter(this,list);

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

    public void pushComment(View v){
        list.add(new RepliedItem(addComment.getText().toString()));
        adapter=new RepliesAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
