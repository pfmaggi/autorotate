package com.pietromaggi.sample.autorotate;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String KEY_ROTATION = "AutoRotate";
    private final static int DEFAULT_ROTATION = 0; // default is to disable the rotation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int value = DEFAULT_ROTATION;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt(KEY_ROTATION);
        }

        android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, value);

        if (android.provider.Settings.System.getInt(getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1){
            Toast.makeText(getApplicationContext(), "Rotation ON", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "Rotation OFF", Toast.LENGTH_SHORT).show();
        }


        finish();

    }
}
