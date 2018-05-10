package com.dragfoundation.screenart;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadServiceSingleBroadcastReceiver;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Address extends AppCompatActivity implements UploadStatusDelegate{

    Button button, place;
    OrderStat orderStat;
    String path;
    String text;
    Uri uri;

    EditText name,flat,street,contact,email;
    String fullAddress;
    LottieAnimationView progress;


    StringRequest stringRequest;

    String submit_url = "http://formpicture.com/vikasdata.php";

    private static final String TAG = "AndroidUploadService";

    private UploadServiceSingleBroadcastReceiver uploadReceiver;


    @Override
    protected void onResume() {
        super.onResume();
        uploadReceiver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        uploadReceiver.unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        name=findViewById(R.id.name);
        flat=findViewById(R.id.add1);
        street=findViewById(R.id.add2);
        contact=findViewById(R.id.phone);
        email=findViewById(R.id.email_text);


        progress=findViewById(R.id.animation10);





        uploadReceiver = new UploadServiceSingleBroadcastReceiver(this);



        orderStat=(OrderStat) getIntent().getSerializableExtra("create");
        path=getIntent().getStringExtra("path");

        uri=Uri.parse(path);

        button=(Button)findViewById(R.id.place);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flat.getText().toString().isEmpty()||street.getText().toString().isEmpty()||name.getText().toString().isEmpty()||contact.getText().toString().isEmpty()||email.getText().toString().isEmpty())
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Please fill all fields", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();

                }
                else{
                    fullAddress=flat.getText().toString()+", "+street.getText().toString();

                    button.setText("Please wait...");
                    button.setEnabled(false);
                    progress.setVisibility(View.VISIBLE);
                    call();
                }


            }
        });
    }

    private void call() {



       // Toast.makeText(this, path, Toast.LENGTH_SHORT).show();

        uploadFile(uri);




        stringRequest = new StringRequest(Request.Method.POST, submit_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Address.this, response, Toast.LENGTH_LONG).show();

                Intent i=new Intent(Address.this,ThankYou.class);
                i.putExtra("orderNo",response);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Address.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("flag", String.valueOf(1));
                params.put("category", String.valueOf(orderStat.getCategory()));
                params.put("weight", String.valueOf(orderStat.getWeight()));
                params.put("type", String.valueOf(orderStat.getType()));
                params.put("quantity", String.valueOf(orderStat.getQuantity()));
                params.put("filename", orderStat.getFileName());
                params.put("description", orderStat.getComments());
                params.put("address", fullAddress);
                params.put("name", name.getText().toString());
                params.put("mobno", contact.getText().toString());
                params.put("custid", orderStat.getName());
                params.put("time", String.valueOf(orderStat.getDate()));
                params.put("email", email.getText().toString());
                return params;
            }
        };



    }








    private void uploadFile(Uri uri) {
        try {

            Intent intent=new Intent(this,Navigation.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent clickIntent = PendingIntent.getActivity(
                    this, 1, intent, PendingIntent.FLAG_ONE_SHOT);


            UploadNotificationConfig config=new UploadNotificationConfig();


            config.setTitleForAllStatuses(orderStat.getFileName());
            //config.setClearOnActionForAllStatuses(true);

           // config.setClickIntentForAllStatuses(clickIntent).setClearOnActionForAllStatuses(true);
            //config.getCompleted().autoClear=true;


            String uploadId = UUID.randomUUID().toString();
            uploadReceiver.setUploadID(uploadId);

            String m =
                    new MultipartUploadRequest(Address.this, uploadId,"http://www.issacitlabsolutionsllp.com/mentor/upl.php")
                            // starting from 3.1+, you can also use content:// URI string instead of absolute file
                            .addFileToUpload(uri.toString(),"myFile")
                            .setNotificationConfig(config)
                            .addParameter("custId",orderStat.getName())
                            .setMaxRetries(3)
                            .startUpload();





        } catch (Exception exc) {
            Log.e("AndroidUploadService", exc.getMessage(), exc);
        }
    }

    @Override
    public void onProgress(Context context, UploadInfo uploadInfo) {

    }

    @Override
    public void onError(Context context, UploadInfo uploadInfo, ServerResponse serverResponse, Exception exception) {
        Toast.makeText(context, "Error in Placing Order", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {

        MySingleton.getInstance(getApplicationContext()).addTorequestQueue(stringRequest);
        Toast.makeText(context, "Placing Your Order", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(Context context, UploadInfo uploadInfo) {

    }
}
