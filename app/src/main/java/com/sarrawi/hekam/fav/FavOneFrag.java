package com.sarrawi.hekam.fav;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sarrawi.hekam.R;
import com.sarrawi.hekam.adapters.MsgFavAdapter;
import com.sarrawi.hekam.adapters.MsgFavAdapter;
import com.sarrawi.hekam.database.DataBase;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Msg;

import java.util.ArrayList;
import java.util.List;

public class FavOneFrag extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<Msg> msg_list = new ArrayList<>();
    DataBase db ;
    private MsgFavAdapter msgFavAdapter;
    DatabaseHelper mDBHelper;
    //////////////////////
    private Typeface Ffont;
    private Typeface font1,font2,font3,font4,font5,font6,font7;
    private String txtView1Size;
    private int textSize;
    public static final String TAG = "Armstring";
    /////////////////////
    public FavOneFrag() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fav_item_one,container,false);
        mRecyclerView =view.findViewById(R.id.recycler_fav_one);
//        db = new DataBase(getActivity());
//        msg_list = db.getFavMessages();
        mDBHelper = new DatabaseHelper(getActivity());

        msg_list = mDBHelper.getFavMessages();

        msgFavAdapter = new MsgFavAdapter(msg_list, getActivity(), Ffont, textSize);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(msgFavAdapter);
        msgFavAdapter.notifyDataSetChanged();
        font1=Typeface.createFromAsset(getActivity().getAssets(),"fonts/a.otf");
        font2=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ab.otf");
        font3=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ac.otf");
        font4=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ad.otf");
        font5=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ae.otf");
        font6=Typeface.createFromAsset(getActivity().getAssets(),"fonts/af.otf");
        font7=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ag.otf");
        return view;
    }





    private void specifyFont(){
//        String fontName = preferences.getString("LIST_OF_FONTS", "Chunkfive.otf");
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        Log.d(TAG, "specifyFont: " + sp);
        if(sp.getInt("font",0)==0){
            Log.d(TAG, "specifyFont: 1");
            font1=Typeface.createFromAsset(getActivity().getAssets(),"fonts/a.otf");
            Ffont = font1;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font1, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==1){
            Log.d(TAG, "specifyFont: 2");

            font2=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ab.otf");
            Ffont = font2;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font2, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==2) {
            Log.d(TAG, "specifyFont: 3");

            font3=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ac.otf");
            Ffont = font3;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font3, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==3) {
            Log.d(TAG, "specifyFont: 4");
            font4=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ad.otf");
            Ffont = font4;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font4, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==4) {
            Log.d(TAG, "specifyFont: 5");

            font5=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ae.otf");
            Ffont = font5;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font5, textSize);
            // mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==5) {
            Log.d(TAG, "specifyFont: 6");

            font6=Typeface.createFromAsset(getActivity().getAssets(),"fonts/af.otf");
            Ffont = font6;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font6, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==6) {
            Log.d(TAG, "specifyFont: 7");
            font7=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ag.otf");
            Ffont = font7;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, getActivity(), font7, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);

        }
        //mRecyclerView.setAdapter(msgTypesAdapter);
        msgFavAdapter.setFont(Ffont);
        msgFavAdapter.notifyDataSetChanged();

    }
    private void specifyFontSize(){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (sp.getInt("fontSize",0)==0)
        {
            msgFavAdapter.setTextSizes(14);
            msgFavAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==1)
        {
            msgFavAdapter.setTextSizes(16);
            msgFavAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==2)
        {
            msgFavAdapter.setTextSizes(18);
            msgFavAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==3)
        {
            msgFavAdapter.setTextSizes(20);
            msgFavAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==4)
        {
            msgFavAdapter.setTextSizes(22);
            msgFavAdapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==5)
        {
            msgFavAdapter.setTextSizes(24);
            msgFavAdapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==6)
        {
            msgFavAdapter.setTextSizes(26);
            msgFavAdapter.notifyDataSetChanged();
        }



    }

    private void AdsView() {
        AdView mAdView;
//        MobileAds.initialize(this, App_ID);
        mAdView = (AdView) view.findViewById(R.id.adView_fav_one);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onResume() {
        super.onResume();
        msgFavAdapter.notifyDataSetChanged();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFontSize();
        AdsView();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFont();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFont();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
    }

}