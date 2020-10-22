package com.sarrawi.hekam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sarrawi.hekam.adapters.Amthal_Adapter;
import com.sarrawi.hekam.adapters.Amthal_Adapter;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.database.SqliteClasses;
import com.sarrawi.hekam.model.Amthal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ThreeFragment extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Amthal_Adapter amthal_Adapter;
    List<Amthal> amthal_list = new ArrayList<>();
    SqliteClasses sqliteClasses;
    private List<Amthal> contactList;
    SharedPreferences preferences;
    private DatabaseHelper mDBHelper;
    //////////////////////
    private Typeface Ffont;
    private Typeface font1,font2,font3,font4,font5,font6,font7;
    private String txtView1Size;
    private int textSize;
    public static final String TAG = "Armstring";
    AdView mAdView;
    /////////////////////
    public ThreeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//Check exists database
        mDBHelper = new DatabaseHelper(getActivity());
        File database = getActivity().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(getActivity())) {
                Toast.makeText(getActivity(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_three,container,false);
        mRecyclerView =view.findViewById(R.id.recycler_frag_three);

        final Amthal m = new Amthal();
        //Get product list in db when db exists
        amthal_list = mDBHelper.getAmthal();

        amthal_Adapter = new Amthal_Adapter(amthal_list, getActivity(), Ffont, textSize);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(amthal_Adapter);

//        List();
        return view;
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        amthal_Adapter.setFont(Ffont);
        amthal_Adapter.notifyDataSetChanged();

    }

//    private void specifyFontSize(){
//        txtView1Size = preferences.getString("TEXT_SIZE","14");
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
//        textSize = Integer.parseInt(txtView1Size);
//        Amthal_Adapter = new Amthal_Adapter(amthal_list, getActivity(), Ffont, textSize);
//        mRecyclerView.setAdapter(Amthal_Adapter);
//    }

    private void specifyFontSize(){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (sp.getInt("fontSize",0)==0)
        {
            amthal_Adapter.setTextSizes(14);
            amthal_Adapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==1)
        {
            amthal_Adapter.setTextSizes(16);
            amthal_Adapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==2)
        {
            amthal_Adapter.setTextSizes(18);
            amthal_Adapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==3)
        {
            amthal_Adapter.setTextSizes(20);
            amthal_Adapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==4)
        {
            amthal_Adapter.setTextSizes(22);
            amthal_Adapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==5)
        {
            amthal_Adapter.setTextSizes(24);
            amthal_Adapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==6)
        {
            amthal_Adapter.setTextSizes(26);
            amthal_Adapter.notifyDataSetChanged();
        }



    }

    private void AdsView() {
        AdView mAdView;
//        MobileAds.initialize(this, App_ID);
        mAdView = (AdView) view.findViewById(R.id.adView_item_three);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onResume() {
        if (this.mAdView != null) {
            this.mAdView.resume();
        }
        super.onResume();
        amthal_Adapter.notifyDataSetChanged();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        AdsView();
        specifyFontSize();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFontSize();
    }

    @Override
    public void onPause() {
        if (this.mAdView != null) {
            this.mAdView.pause();
        }
        super.onPause();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFontSize();
    }

    @Override
    public void onDestroy() {
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
        super.onDestroy();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        specifyFont();
        specifyFontSize();
    }
}
