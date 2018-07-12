package com.pietromaggi.sample.autorotate;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String KEY_ROTATION = "AutoRotate";
    private final static int DEFAULT_ROTATION = 0; // default is to disable the rotation

    @TargetApi(23)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int value = DEFAULT_ROTATION;
        boolean bDoIt = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt(KEY_ROTATION);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.System.canWrite(getApplicationContext())) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
//                startActivity(intent);
//                bDoIt = false;
//            }
//        }

        if (bDoIt) {
            android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, value);

            if (android.provider.Settings.System.getInt(getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
                Toast.makeText(getApplicationContext(), "Rotation ON", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Rotation OFF", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
