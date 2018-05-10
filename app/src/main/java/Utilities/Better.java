package Utilities;

import android.app.Dialog;
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

public class Better extends Dialog implements View.OnClickListener {

    public Context activity;
    public Dialog dialog;

    private TextView yes,no;

    public Better(Context c) {
        super(c);
        this.activity=c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dobetter);

        yes=(TextView)findViewById(R.id.yes);
        yes.setOnClickListener(this);

        no=(TextView)findViewById(R.id.no);
        no.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.no :
            this.dismiss();
            break;

            case R.id.yes :
                this.dismiss();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"surajjain.art@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Customer] Feedback");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(intent);
                }
                break;

        }

    }
}

