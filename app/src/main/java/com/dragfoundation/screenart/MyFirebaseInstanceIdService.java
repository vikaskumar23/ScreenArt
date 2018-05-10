package com.dragfoundation.screenart;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by vikas on 07-10-2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN="REGISTRATION TOKEN";
    SharedPreferences user;
    SharedPreferences.Editor editor;
    @Override
    public void onTokenRefresh() {
        String recentToken= FirebaseInstanceId.getInstance().getToken();
        user=getApplicationContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        editor=user.edit();
        editor.clear();
        editor.putString("FirebaseID",recentToken);
        editor.commit();
        Log.d(REG_TOKEN,recentToken);
    }
}
