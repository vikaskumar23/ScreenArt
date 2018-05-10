package com.dragfoundation.screenart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankYou extends AppCompatActivity {

    TextView call;
    TextView mail;
    Button great;
    TextView orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        String x=getIntent().getStringExtra("orderNo");

        orderId=findViewById(R.id.orderid);
        int o=Integer.parseInt(x);
        int n=00000+o;
        orderId.setText("Your Order Id is SA00000"+n);

        great=(Button)findViewById(R.id.great);
        great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThankYou.this, Navigation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void caller(View view)
    {
        call=(TextView)findViewById(R.id.q2);
        String a=call.getText().toString();
        String uri = "tel:" + a.trim() ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void mailer(View view)
    {
        mail=(TextView)findViewById(R.id.q1);
        String a=mail.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{a});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Query");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ThankYou.this,Navigation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //super.onBackPressed();
    }
}
