package com.kh618.entmaa.Activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.kh618.entmaa.R;

import java.net.URI;
import java.net.URISyntaxException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarCodeScanner extends Activity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
       // setContentView(R.layout.activity_scanner);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

            }
        }
        scannerView= new ZXingScannerView(this);
       // ViewGroup contentFrame =  findViewById(R.id.content_frame);
       // contentFrame.addView(scannerView);
        setContentView(scannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        if (result !=null) {
            try {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText()));
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
    }

}
