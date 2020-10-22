package com.sarrawi.hekam;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.NavigationView;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.sarrawi.hekam.fav.FavActivity;
import com.sarrawi.hekam.picPager.ViewPagerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    TabLayout tabLayout;
    ViewPager viewPager;
    Snackbar snackbar;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(),"حكم");
//        adapter.addFragment(new TowFragment(),"الصور");
        adapter.addFragment(new ThreeFragment(),"أمثال");

        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);

//        prepareAd();
//        showAds();

//        Snack();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView rightNavigationView = (NavigationView) findViewById(R.id.nav_right_view);
        rightNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int id = item.getItemId();

//                if (id == R.id.nav_azkar) {
////                    Intent i =new Intent(MainActivity.this,MainActivity.class);
////                    startActivity(i);
//
//                }
                 if (id == R.id.nav_fav) {
                    Intent i =new Intent(MainActivity.this, FavActivity.class);
                    startActivity(i);

                }


                 else if (id == R.id.nav_zak) {
                     Intent i =new Intent(MainActivity.this,Text.class);
                     startActivity(i);

                 }

                else if (id == R.id.nav_seit) {
                    Intent i =new Intent(MainActivity.this,SettingsActivity.class);
                    startActivity(i);

                }


                drawer.closeDrawer(GravityCompat.END); /*Important Line*/
                return true;
            }
        });
    }

    private void Snack() {
        View view = findViewById(R.id.relative_id);
        snackbar=Snackbar.make(view,"aaaaaaaaaaaaaaaaaa",Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(3000);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        View v = snackbar.getView();
        v.setBackgroundColor(getResources().getColor(R.color.BackgroundSnackBarColor));

        TextView txt =(TextView)v.findViewById(R.id.snackbar_text);
        txt.setTextColor(getResources().getColor(R.color.TextSnackBarColor));

//        TextView txt_Action =(TextView)v.findViewById(android.support.design.R.id.snackbar_action);
        TextView txt_Action =(TextView)v.findViewById(R.id.snackbar_action);
        txt_Action.setTextColor(getResources().getColor(R.color.ActionSnackBarColor));
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_openRight) {
            drawer.openDrawer(GravityCompat.END);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void  prepareAd(){

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1895204889916566/9140796492");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
//                Toast.makeText(FavActivity.this, "Ad is closed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showAds() {

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            public void run() {
                Log.i("hello", "world");
                runOnUiThread(new Runnable() {
                    public void run() {

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG"," Interstitial not loaded");
                        }
                        prepareAd();
                    }
                });

            }
        }, 1, 1, TimeUnit.MINUTES);
    }
}
