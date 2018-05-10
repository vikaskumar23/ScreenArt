package com.dragfoundation.screenart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.login.LoginManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import Utilities.Rate;

import static com.dragfoundation.screenart.R.id.orders;
import static com.dragfoundation.screenart.R.id.address;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView.LayoutManager mLayoutManager1;

    private RecyclerView mRecyclerView2;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager2;


    String userid, userdata;
    ProfileTracker profileTracker;
    SharedPreferences user;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userinfo();

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Navigation.this,CreateOrder.class);
                startActivity(intent);
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        SliderAdapter adapterView = new SliderAdapter(this);
        mViewPager.setAdapter(adapterView);


        mViewPager.setCurrentItem(0);
        adapterView.setTimer(mViewPager, 7);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);


        //new ui

        productList();
        serviceList();
        designList();

        //productlist();


    }

    private void productList() {

        final ArrayList<Catalogue> cat = new ArrayList<Catalogue>();

        cat.add(new Catalogue(R.drawable.crt, "Visiting card", 130, 100));
        cat.add(new Catalogue(R.drawable.letter, "Letter Heads", 200, 100));
        cat.add(new Catalogue(R.drawable.book, "Books", 100, 1));
        cat.add(new Catalogue(R.drawable.proceeding, "Procceeding", 130, 1));
        cat.add(new Catalogue(R.drawable.poster, "Posters", 800, 100));


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CatalogueAdapter(cat,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);
    }

    private void serviceList() {

        final ArrayList<Catalogue> cat = new ArrayList<Catalogue>();

        cat.add(new Catalogue(R.drawable.lamination, "Lamination", 200, 100));
        cat.add(new Catalogue(R.drawable.cut, "Cutting", 100, 500));
        cat.add(new Catalogue(R.drawable.binding, "Binding", 200, 10));



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view1);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CatalogueAdapter(cat,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);
    }

    private void designList() {
        final ArrayList<Catalogue> cat = new ArrayList<Catalogue>();

        cat.add(new Catalogue(R.drawable.crt, "Visiting card", 500, 1));
        cat.add(new Catalogue(R.drawable.letter, "Letter Heads", 500, 1));
        cat.add(new Catalogue(R.drawable.book, "Books", 1000, 1));
        cat.add(new Catalogue(R.drawable.poster, "Posters", 800, 1));


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CatalogueAdapter(cat,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);
    }

    public void create(View view) {
        Intent intent = new Intent(Navigation.this,OrderPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.support) {
            Intent intent = new Intent(this, Support.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == orders) {

            Intent intent = new Intent(this, order.class);
            startActivity(intent);

        } else if (id == R.id.profile) {

            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);

        } else if (id == address) {
            Intent intent = new Intent(this, Offers.class);
            startActivity(intent);

        } else if (id == R.id.chat) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.help) {
            Intent intent = new Intent(this, FAQ.class);
            startActivity(intent);

        } else if (id == R.id.rate) {

            Rate rate = new Rate(Navigation.this);
            rate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            rate.setCancelable(true);
            rate.show();

        } else if (id == R.id.share) {
            shareApplication();


        } else if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);

        } else if (id == R.id.logout) {

            // logout of Account Kit
            AccountKit.logOut();
            // logout of Login Button
            LoginManager.getInstance().logOut();

            // let MainActivity know the user logged out
            setResult(RESULT_OK);
            finish();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void userinfo() {

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    getid(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() != null) {
            // If there is an access token then Login Button was used
            // Check if the profile has already been fetched
            Profile currentProfile = Profile.getCurrentProfile();
            if (currentProfile != null) {
                getid(currentProfile);

            } else {
                // Fetch the profile, which will trigger the onCurrentProfileChanged receiver
                Profile.fetchProfileForCurrentAccessToken();
            }

        } else {
            // Otherwise, get Account Kit login information
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    // get Account Kit ID
                    userid = account.getId();
                    user = getApplicationContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                    editor = user.edit();
                    editor.clear();
                    editor.putString("ID", userid);
                    editor.commit();

                }

                @Override
                public void onError(final AccountKitError error) {
                    String toastMessage = error.getErrorType().getMessage();
                    Toast.makeText(Navigation.this, toastMessage, Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    private void getid(Profile currentProfile) {

        userid = currentProfile.getId();
        user = getApplicationContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        editor = user.edit();
        editor.clear();
        editor.putString("ID", userid);
        editor.commit();
    }


    private void shareApplication() {
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("application/vnd.android.package-archive");

        // Append file and sendchat Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ", "").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
            Uri fileURI = FileProvider.getUriForFile(Navigation.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    tempFile);
            intent.putExtra(Intent.EXTRA_STREAM, fileURI);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Share Screen Art via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.startTracking();
    }
}
