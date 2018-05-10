package com.dragfoundation.screenart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletedFragment2 extends Fragment {
    ProgressDialog progressDialog;
    StringRequest stringRequest;
    String url = "http://formpicture.com/vikasdata.php";
    SharedPreferences user;
    String username;

    PendingOrderAdapter2 adapter;

    public CompletedFragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pending, container, false);



        user = getContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);

        username = user.getString("ID", null);



        final ArrayList<OrderStat> orders = new ArrayList<OrderStat>();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching your Pending orders....");
        progressDialog.show();


        adapter = new PendingOrderAdapter2(getActivity(), orders);

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);

                    JSONArray data=object.getJSONArray("mydata");
                    for(int i=0;i<data.length();i++)
                    {
                        JSONObject order=data.getJSONObject(i);
                        int orderid=order.getInt("orderid");
                        int category=order.getInt("category");
                        int weight=order.getInt("weight");
                        int type=order.getInt("type");
                        int quantity=order.getInt("quantity");
                        String fileName=order.getString("age");
                        String description=order.getString("description");
                        String address=order.getString("address");
                        String name=order.getString("name");
                        String contact=order.getString("mobno");
                        String id=order.getString("custid");
                        long time=order.getLong("tim");
                        String email=order.getString("emailID");
                        OrderStat x=new OrderStat(orderid,id,time,quantity,category,type,weight,fileName,description,address);
                        orders.add(x);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
               // Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Something went Wrong....", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("flag", "3");
                params.put("custid", username);
                return params;
            }
        };

        if(isNetworkAvailable())
        {
            MySingleton.getInstance(getContext()).addTorequestQueue(stringRequest);
        }else
        {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }





        final RecyclerView listView = (RecyclerView) rootView.findViewById(R.id.pendingOrders);

        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        return rootView;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }


}
