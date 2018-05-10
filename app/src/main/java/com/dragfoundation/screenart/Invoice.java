package com.dragfoundation.screenart;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.Calendar;

public class Invoice extends AppCompatActivity {

    ImageButton img,img2,img3;
    View a;

    TextView date,item,desc,address,code;
    OrderStat orderStat;
    String time,cat,wt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getSupportActionBar().setElevation(0);

        orderStat=(OrderStat) getIntent().getSerializableExtra("data");

        date=findViewById(R.id.dateInvoice);
        item=findViewById(R.id.itemname);
        desc=findViewById(R.id.paperWeight);
        address=findViewById(R.id.addressInvoice);
        code=findViewById(R.id.tv_invoice_code);

        Calendar s=Calendar.getInstance();
        s.setTimeInMillis(orderStat.getDate());

        time=DateFormat.format("h:mm a", s).toString()+", "+DateFormat.format("dd MMMM, yyyy", s).toString();

        date.setText(time);
        switch (orderStat.getWeight())
        {
            case 1: wt="Paper Weight : 100 GSM";
                break;
            case 2: wt="Paper Weight : 130 GSM";
                break;
            case 3: wt="Paper Weight : 170 GSM";
                break;
            case 4: wt="Paper Weight : 250 GSM";
                break;
            case 5: wt="Paper Weight : 270 GSM";
                break;
            case 6: wt="Paper Weight : 300 GSM";
                break;
        }

        switch (orderStat.getCategory())
        {
            case 1: cat="Visiting cards";
                break;
            case 2: cat="Letter Heads";
                break;
            case 3: cat="Envelopes";
                break;
            case 4: cat="Books";
                break;
            case 5: cat="Marriage Cards";
                break;
        }

        item.setText(cat);

        desc.setText(wt+"\n"+"File name : "+orderStat.getFileName()+"\n"+"Comments : "+orderStat.getComments());

        address.setText(orderStat.getAdd());

        code.setText("SA-00000"+orderStat.getOrder());


        final ExpandableRelativeLayout expandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        final ExpandableRelativeLayout expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        final ExpandableRelativeLayout expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        /*expandableLayout.collapse();
        expandableLayout2.collapse();
        expandableLayout3.collapse();*/
        img=(ImageButton)findViewById(R.id.bt_toggle_items);
        img2=(ImageButton)findViewById(R.id.bt_toggle_items2);
        img3=(ImageButton)findViewById(R.id.bt_toggle_items3);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                   expandableLayout.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandableLayout.collapse();
                }

            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandableLayout2.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandableLayout2.collapse();
                }

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 0.0f) {
                    v.animate().setDuration(200).rotation(180.0f);
                    expandableLayout3.expand();
                }
                else{
                    v.animate().setDuration(200).rotation(0.0f);
                    expandableLayout3.collapse();
                }

            }
        });


    }
}
