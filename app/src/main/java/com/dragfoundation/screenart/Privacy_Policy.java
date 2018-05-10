package com.dragfoundation.screenart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Privacy_Policy extends AppCompatActivity {

    TextView gps,fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy__policy);

        gps=(TextView)findViewById(R.id.gps);
        fa=(TextView)findViewById(R.id.fa);

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/policies/privacy/"));
                startActivity(myIntent);
            }
        });


        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebase.google.com/policies/analytics/"));
                startActivity(myIntent);
            }
        });


    }
}
