package Utilities;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dragfoundation.screenart.R;

/**
 * Created by vikas on 04-10-2017.
 */

public class RateApp extends Dialog implements View.OnClickListener {
    public Context activity;
    public Dialog dialog;

    TextView nope;
    TextView yeah;

    public RateApp(Context c) {
        super(c);
        this.activity=c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rate_app);


        nope = (TextView) findViewById(R.id.nope);
        nope.setOnClickListener(this);

        yeah = (TextView) findViewById(R.id.yeah);
        yeah.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.nope :
                this.dismiss();
                break;

            case R.id.yeah:
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    getContext().startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
                break;


        }



    }


}