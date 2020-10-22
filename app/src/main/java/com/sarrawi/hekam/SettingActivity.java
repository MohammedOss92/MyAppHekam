package com.sarrawi.hekam;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    Spinner spFont,spfontSiz;
    int theSelectedFontPosition;
    int theselectedfontSize;

    ArrayAdapter<String> aFont,bfontSize,cBackground;

    String font [] = {"الخط الافتراضي","الخط الاول","الخط الثاني","الخط الثالث","الخط الرابع","الخط الخامس","الخط السادس"};
    String fontSize [] = {"اللون الافتراضي","اللون الاول","اللون الثاني","اللون الثالث","اللون الرابع"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        aFont=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,font);
        bfontSize=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fontSize);

        spFont=(Spinner) findViewById(R.id.spinner1);
        spfontSiz=(Spinner) findViewById(R.id.spinner2);

        spFont.setAdapter(aFont);
        spfontSiz.setAdapter(bfontSize);


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

        spfontSiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                theselectedfontSize=position;

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
                SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor e=sp.edit();

                e.putInt("font", theSelectedFontPosition);
                e.putInt("fontSize", theselectedfontSize);
                e.commit();
            }
        });

        Ffont();
        CFont();
        save();


    }

    public void save()
    {
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor e=sp.edit();

        e.putInt("font", theSelectedFontPosition);
        e.putInt("fontSize", theselectedfontSize);
        e.commit();

    }

    public void savea(View v)
    {
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor e=sp.edit();

        e.putInt("font", theSelectedFontPosition);
        e.putInt("fontSize", theselectedfontSize);
        e.commit();

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
            spfontSiz.setSelection(0);
        }

        else if(sp.getInt("fontSize", 0)==1)
        {
            spfontSiz.setSelection(1);
        }

        else if(sp.getInt("fontSize", 0)==2)
        {
            spfontSiz.setSelection(2);
        }

        else if(sp.getInt("fontSize", 0)==3)
        {
            spfontSiz.setSelection(3);
        }

        else if(sp.getInt("fontSize", 0)==4)
        {
            spfontSiz.setSelection(4);
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
        save();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
        save();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
        save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
        save();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sp2=getSharedPreferences("MyPref", MODE_WORLD_WRITEABLE);
        Ffont();
        CFont();
        save();
    }
}
