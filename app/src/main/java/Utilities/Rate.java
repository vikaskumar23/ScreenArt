package Utilities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dragfoundation.screenart.R;

/**
 * Created by vikas on 04-10-2017.
 */

public class Rate extends Dialog implements View.OnClickListener {

    public Activity activity;
    public Dialog dialog;

    private LinearLayout snap,pdf;
    ImageView close;

    public Rate(Activity c) {
        super(c);
        this.activity=c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rate);

        close=(ImageView)findViewById(R.id.close);
        close.setOnClickListener(this);

        snap = (LinearLayout) findViewById(R.id.loved);
        snap.setOnClickListener(this);

        pdf = (LinearLayout) findViewById(R.id.meh);
        pdf.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.close :
                this.dismiss();
                break;
            case R.id.loved :
                this.dismiss();
                RateApp rate = new RateApp(getContext());
                rate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                rate.setCancelable(true);
                rate.show();
                break;
            case R.id.meh :
                this.dismiss();
                Better bet = new Better(getContext());
                bet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bet.setCancelable(true);
                bet.show();
                break;


        }

    }
}
