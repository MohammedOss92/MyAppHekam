package com.sarrawi.hekam.fav;

//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> favfragmentList = new ArrayList<>();
    private final List<String> favfragmentTitleList = new ArrayList<>();


    public FavViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return favfragmentList.get(i);
    }

    @Override
    public int getCount() {
        return favfragmentTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return favfragmentTitleList.get(position);
    }

    public void addFragment (Fragment fragment,String title)
    {
        favfragmentList.add(fragment);
        favfragmentTitleList.add(title);
    }


}
