package com.sarrawi.hekam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SetActivity extends AppCompatActivity {
    Spinner spFont,spfontSize;
    int theSelectedFontPosition;
    int theselectedcolorfont;

    ArrayAdapter<String> aFont,bfontSize,cBackground;

    String font [] = {"الخط الافتراضي","الخط الاول","الخط الثاني","الخط الثالث","الخط الرابع","الخط الخامس","الخط السادس"};
    String fontSize [] = {"اللون الافتراضي","اللون الاول","اللون الثاني","اللون الثالث","اللون الرابع"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aFont=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,font);
        bfontSize=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fontSize);

        spFont=(Spinner) findViewById(R.id.spinner1);
        spfontSize=(Spinner) findViewById(R.id.spinner2);

        spFont.setAdapter(aFont);
        spfontSize.setAdapter(bfontSize);


        spFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                theSelectedFontPosition=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spfontSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                theselectedcolorfont=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);

        Button b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(SetActivity.this);
                //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor e=sp.edit();

                e.putInt("font", theSelectedFontPosition);
                e.putInt("fontSize", theselectedcolorfont);
                e.commit();
            }
        });

        Ffont();
        CFont();


    }



    public void Ffont ()
    {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);

        if(sp.getInt("font", 0)==0)
        {

            spFont.setSelection(0);
        }
        else if(sp.getInt("font", 0)==1)
        {
            spFont.setSelection(1);
        }

        if(sp.getInt("font", 0)==2)
        {

            spFont.setSelection(2);
        }
        else if(sp.getInt("font", 0)==3)
        {
            spFont.setSelection(3);
        }

        if(sp.getInt("font", 0)==4)
        {

            spFont.setSelection(4);
        }
        else if(sp.getInt("font", 0)==5)
        {
            spFont.setSelection(5);
        }

        else if(sp.getInt("font", 0)==6)
        {
            spFont.setSelection(6);
        }
    }

    public void CFont()
    {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);

        if(sp.getInt("fontSize", 0)==0)
        {
            spfontSize.setSelection(0);
        }

        else if(sp.getInt("fontSize", 0)==1)
        {
            spfontSize.setSelection(1);
        }

        else if(sp.getInt("fontSize", 0)==2)
        {
            spfontSize.setSelection(2);
        }

        else if(sp.getInt("fontSize", 0)==3)
        {
            spfontSize.setSelection(3);
        }

        else if(sp.getInt("fontSize", 0)==4)
        {
            spfontSize.setSelection(4);
        }
        else if(sp.getInt("fontSize", 0)==5)
        {
            spfontSize.setSelection(4);
        }
        else if(sp.getInt("fontSize", 0)==6)
        {
            spfontSize.setSelection(4);
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
    }
}
