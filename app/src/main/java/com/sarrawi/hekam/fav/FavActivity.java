package com.sarrawi.hekam.fav;

import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sarrawi.hekam.R;

public class FavActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Toolbar toolbar = findViewById(R.id.fav_tool);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.fav_viewpager_id);
        FavViewPagerAdapter favadapter = new FavViewPagerAdapter(getSupportFragmentManager());
        favadapter.addFragment(new FavOneFrag(),"حكم");
        favadapter.addFragment(new FavTowFrag(),"أمثال");

        viewPager.setAdapter(favadapter);
        tabLayout = (TabLayout) findViewById(R.id.fav_tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav, menu);
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
//            drawer.openDrawer(GravityCompat.END);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}


