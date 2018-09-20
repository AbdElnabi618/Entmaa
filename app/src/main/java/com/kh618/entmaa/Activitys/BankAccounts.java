package com.kh618.entmaa.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.kh618.entmaa.Adapter.BankAdapter;
import com.kh618.entmaa.MyClasses.BankItem;
import com.kh618.entmaa.MyClasses.MyNavigation;
import com.kh618.entmaa.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class BankAccounts extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView uploadPhoto ;
    TextView uploadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_accounts);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView backRow = findViewById(R.id.backrow);
        uploadPhoto = findViewById(R.id.img_photoUpload);
        uploadText =findViewById(R.id.text_photoUpload);

        String local = Locale.getDefault().toString();
        if(local.equals("ar_EG") || local.equals("ar"))
            backRow.setImageResource(R.mipmap.arrow);

        drawerLayout = findViewById(R.id.drawer_banks);
        navigationView = findViewById(R.id.navigation_banks);
        new MyNavigation(this,drawerLayout,navigationView);

        ArrayList<BankItem> list = new ArrayList<>();
        list.add(new BankItem(R.mipmap.bg_bank,getResources().getString(R.string.bank_Alahly)
                ,"1112 2223 3334 4445",R.mipmap.bank_logo));
        list.add(new BankItem(R.mipmap.bg_bank2,getResources().getString(R.string.ragahy_bank)
                ,"1112 2223 5556 4445",R.mipmap.bank_logo));
        list.add(new BankItem(R.mipmap.bg_bank3,getResources().getString(R.string.bank_Alahly)
                ,"1112 2223 3334 4445",R.mipmap.bank_logo));
        list.add(new BankItem(R.mipmap.bg_bank2,getResources().getString(R.string.ragahy_bank)
                ,"1112 2223 5556 4445",R.mipmap.bank_logo));

        BankAdapter adapter = new BankAdapter(this,list);

        RecyclerView recyclerView = findViewById(R.id.bank_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

    public void pickUpPhoto(View v){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,18);
    }

    public void openDrawer(View v) {
        drawerLayout.openDrawer(Gravity.START,true);
    }

    public void backRow(View view) {
        finish();
    }

    public void Send(View v){
        uploadText.setVisibility(View.VISIBLE);
        uploadPhoto.setImageResource(R.mipmap.upload_photo);
        Intent i = new Intent(BankAccounts.this,Check.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            try {
                Uri uri= data.getData();
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                uploadPhoto.setImageBitmap(bitmap);
                uploadText.setVisibility(View.INVISIBLE);
            }catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();

        }
    }
}
