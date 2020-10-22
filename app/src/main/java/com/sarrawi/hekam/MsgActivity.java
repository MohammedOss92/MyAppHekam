package com.sarrawi.hekam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.view.GravityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sarrawi.hekam.R;
import com.sarrawi.hekam.adapters.MsgAdapter;
import com.sarrawi.hekam.adapters.MsgAdapter;
import com.sarrawi.hekam.database.DataBase;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Msg;
import com.sarrawi.hekam.model.MsgTypes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends AppCompatActivity {
    RecyclerView recycler_msg;
    int titleID;
    MsgAdapter msgAdapter;
    List<Msg>msg_list = new ArrayList<>();
    DatabaseHelper mDBHelper;
    //////////////////////
    private Typeface Ffont;
    private Typeface font1,font2,font3,font4,font5,font6,font7;
    private String txtView1Size;
    private int textSize;
    public static final String TAG = "Armstring";
    AdView mAdView;
    /////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        Toolbar toolbar = findViewById(R.id.toolbar_msg);
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        titleID=i.getExtras().getInt("titleID");


        recycler_msg=(RecyclerView)findViewById(R.id.recycler_msg);

        mDBHelper = new DatabaseHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        final MsgTypes m = new MsgTypes();
        //Get product list in db when db exists
        msg_list = mDBHelper.getAllMsg(titleID);


        msgAdapter=new MsgAdapter(msg_list,MsgActivity.this, Ffont, textSize);

        recycler_msg.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_msg.setLayoutManager(mLayoutManager);
        recycler_msg.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_msg.setItemAnimator(new DefaultItemAnimator());


        recycler_msg.setAdapter(msgAdapter);
        msgAdapter.notifyDataSetChanged();

//        Msg_List();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
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
            Log.w("MessageActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onResume() {
        if (this.mAdView != null) {
            this.mAdView.resume();
        }
        super.onResume();
        msgAdapter.notifyDataSetChanged();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        specifyFont();
        AdsView();
        specifyFontSize();
    }
    private void specifyFont(){
//        String fontName = preferences.getString("LIST_OF_FONTS", "Chunkfive.otf");
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        Log.d(TAG, "specifyFont: " + sp);
        if(sp.getInt("font",0)==0){
            Log.d(TAG, "specifyFont: 1");
            font1=Typeface.createFromAsset(this.getAssets(),"fonts/a.otf");
            Ffont = font1;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font1, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==1){
            Log.d(TAG, "specifyFont: 2");

            font2=Typeface.createFromAsset(this.getAssets(),"fonts/ab.otf");
            Ffont = font2;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font2, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==2) {
            Log.d(TAG, "specifyFont: 3");

            font3=Typeface.createFromAsset(this.getAssets(),"fonts/ac.otf");
            Ffont = font3;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font3, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==3) {
            Log.d(TAG, "specifyFont: 4");
            font4=Typeface.createFromAsset(this.getAssets(),"fonts/ad.otf");
            Ffont = font4;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font4, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==4) {
            Log.d(TAG, "specifyFont: 5");

            font5=Typeface.createFromAsset(this.getAssets(),"fonts/ae.otf");
            Ffont = font5;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font5, textSize);
            // mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==5) {
            Log.d(TAG, "specifyFont: 6");

            font6=Typeface.createFromAsset(this.getAssets(),"fonts/af.otf");
            Ffont = font6;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font6, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);
        }
        else if(sp.getInt("font",0)==6) {
            Log.d(TAG, "specifyFont: 7");
            font7=Typeface.createFromAsset(this.getAssets(),"fonts/ag.otf");
            Ffont = font7;
            //msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this, font7, textSize);
            //mRecyclerView.setAdapter(msgTypesAdapter);

        }
        //mRecyclerView.setAdapter(msgTypesAdapter);
        msgAdapter.setFont(Ffont);
        msgAdapter.notifyDataSetChanged();

    }


//    private void specifyFontSize(){
//        txtView1Size = preferences.getString("TEXT_SIZE","14");
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
//        textSize = Integer.parseInt(txtView1Size);
//        MsgAdapter = new MsgAdapter(msg_list, this, Ffont, textSize);
//        recycler_msg.setAdapter(MsgAdapter);
//    }


    private void AdsView() {

//        MobileAds.initialize(this, App_ID);
        mAdView = (AdView) findViewById(R.id.adView_msg);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    private void specifyFontSize(){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);

        if (sp.getInt("fontSize",0)==0)
        {
            msgAdapter.setTextSizes(14);
            msgAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==1)
        {
            msgAdapter.setTextSizes(16);
            msgAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==2)
        {
            msgAdapter.setTextSizes(18);
            msgAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==3)
        {
            msgAdapter.setTextSizes(20);
            msgAdapter.notifyDataSetChanged();
        }
        else if (sp.getInt("fontSize",0)==4)
        {
            msgAdapter.setTextSizes(22);
            msgAdapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==5)
        {
            msgAdapter.setTextSizes(24);
            msgAdapter.notifyDataSetChanged();
        }

        else if (sp.getInt("fontSize",0)==6)
        {
            msgAdapter.setTextSizes(26);
            msgAdapter.notifyDataSetChanged();
        }



    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        specifyFont();
        specifyFontSize();
    }

    @Override
    public void onPause() {
        if (this.mAdView != null) {
            this.mAdView.pause();
        }
        super.onPause();
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        specifyFont();
        specifyFontSize();
    }

    @Override
    public void onDestroy() {
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
        super.onDestroy();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
//        specifyFont();
//        specifyFontSize();
    }


}
