package com.dragfoundation.screenart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by vikas on 25-08-2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Start home activity
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        // close splash activity
        finish();
    }
}
