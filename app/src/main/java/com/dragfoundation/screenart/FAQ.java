package com.dragfoundation.screenart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class FAQ extends AppCompatActivity{

    ImageButton a,b,c,d,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        a=(ImageButton)findViewById(R.id.toggle1);
        b=(ImageButton)findViewById(R.id.toggle2);
        c=(ImageButton)findViewById(R.id.toggle3);
        d=(ImageButton)findViewById(R.id.toggle4);
        e=(ImageButton)findViewById(R.id.toggle5);

        final ExpandableRelativeLayout expandable1 = (ExpandableRelativeLayout) findViewById(R.id.expandable);

        final ExpandableRelativeLayout expandable2 = (ExpandableRelativeLayout) findViewById(R.id.expandable2);

        final ExpandableRelativeLayout expandable3 = (ExpandableRelativeLayout) findViewById(R.id.expandable3);

        final ExpandableRelativeLayout expandable4 = (ExpandableRelativeLayout) findViewById(R.id.expandable4);

        final ExpandableRelativeLayout expandable5 = (ExpandableRelativeLayout) findViewById(R.id.expandable5);


        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandable1.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandable1.collapse();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandable2.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandable2.collapse();
                }
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandable3.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandable3.collapse();
                }
            }
        });


        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandable4.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandable4.collapse();
                }
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandable5.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandable5.collapse();
                }
            }
        });


    }
}
