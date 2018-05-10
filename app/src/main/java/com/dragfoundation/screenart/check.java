package com.dragfoundation.screenart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class check extends AppCompatActivity {

    TextView a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        a=(TextView)findViewById(R.id.pro);
        b=(TextView)findViewById(R.id.weigh);
        c=(TextView)findViewById(R.id.qua);
        d=(TextView)findViewById(R.id.prc);

        OrderStat item = (OrderStat) getIntent().getSerializableExtra("order");
        String ab= String.valueOf(item.getQuantity());
        c.setText("Quantity : "+ab);

    }
}
