package com.sarrawi.hekam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sarrawi.hekam.Utils.Utils;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Msg;

import java.util.List;

public class PagerMessage extends AppCompatActivity {
//
//    /**
//     * The {@link android.support.v4.view.PagerAdapter} that will provide
//     * fragments for each of the sections. We use a
//     * {@link FragmentPagerAdapter} derivative, which will keep every
//     * loaded fragment in memory. If this becomes too memory intensive, it
//     * may be best to switch to a
//     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
//     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    List<Msg> msg_list;
    int titleID;
    int fav;
    boolean sourceISFav;
    int msgId=0;
    String filter="";
    SharedPreferences preferences;
    String txtView1Size;
    Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_message);

        Toolbar a = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(a);

        Intent i=getIntent();
        titleID=i.getExtras().getInt("titleID");
        fav=i.getExtras().getInt("fav");
        int pos=i.getExtras().getInt("pos");
        msgId=i.getExtras().getInt("msgID");
        filter=i.getExtras().getString("filter");
        // in case of fav the origPos sent from FavMessages as favOrder
        // but in case of normal message it sent as origpos
        sourceISFav=i.getExtras().getBoolean("sourceIsFav");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        DatabaseHelper ss=new DatabaseHelper(getApplicationContext());
        if(sourceISFav)
        {
            //we have to keep it ASC in Fav because of pager position
            //because we use its order while in normal messages it is not favorder but it is origposition
            msg_list = ss.getFavMessages() ;
        }
        else {
            msg_list = ss.getMessagesNotOrdered(titleID);
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(pos);

        mViewPager.setPageTransformer(true,new RotateDownTransformer());


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_pager_message, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        AdView adView;
        Typeface tf;
        SharedPreferences preferences;
        String txtView1Size;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        TextView tvMsg,tvTitle;
        ImageView fav;
        /////////////////////////////

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber,String msg,Msg m) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("Resaleh", msg);
            //args.putParcelable("MsgObject", (Parcelable) m);
            args.putParcelable("MsgObject",  m);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setHasOptionsMenu(true);
            preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pager_message, container, false);
            final DatabaseHelper data=new DatabaseHelper(getActivity());
            tvMsg=(TextView)rootView.findViewById(R.id.tvMsg);
            tvTitle=(TextView)rootView.findViewById(R.id.tvTitle);
            fav = (ImageView)rootView.findViewById(R.id.imgFavaa);
            adView=(AdView)rootView.findViewById(R.id.adView_);
            adView.loadAd(new AdRequest.Builder().build());


            String msg=getArguments().getString("Resaleh");
            final Msg m=getArguments().getParcelable("MsgObject");
            final DatabaseHelper s=new DatabaseHelper(getActivity());
            String titleDesc=s.getMsgTitleByTitleID(m.getID_Categry());
            tvTitle.setText(titleDesc);
            tvMsg.setText(msg);

            if(s.getIFMsgIsFav(m)==1)
            {
                fav.setImageResource(R.mipmap.f);
            }
            else
            {
                fav.setImageResource(R.mipmap.nf);
            }

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(s.getIFMsgIsFav(m)==0) {
                        fav.setImageResource(R.mipmap.f);
                        s.changeFav( m,1);
                        Toast.makeText(getActivity(), "تم الإضافة للمفضله", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {  fav.setImageResource(R.mipmap.nf);
                        s.changeFav(m, 0);
                        Toast.makeText(getActivity(), "تم الإزالة من المفضله", Toast.LENGTH_SHORT).show();
//                        notify();

                    }
                }
            });





            return rootView;



        }
        private void specifyFontFamilyFromList() {
            String font = preferences.getString("LIST_OF_FONTS", "Chunkfive.otf");
            Typeface typeface;
            switch (font) {
                case "a.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/a.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "ab.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ab.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "ac.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ac.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "ad.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ad.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "ae.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ae.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "af.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/af.otf");
                    tvMsg.setTypeface(typeface);
                    break;
                case "ag.otf":
                    typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ag.otf");
                    tvMsg.setTypeface(typeface);
                    break;
            }
        }

        private void specifyFontSize(){
            SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());

            if (sp.getInt("fontSize",0)==0)
            {
                tvMsg.setTextSize(14);
            }
            else if (sp.getInt("fontSize",0)==1)
            {
                tvMsg.setTextSize(16);
            }
            else if (sp.getInt("fontSize",0)==2)
            {
                tvMsg.setTextSize(18);
            }
            else if (sp.getInt("fontSize",0)==3)
            {
                tvMsg.setTextSize(20);
            }
            else if (sp.getInt("fontSize",0)==4)
            {
                tvMsg.setTextSize(22);
            }

            else if (sp.getInt("fontSize",0)==5)
            {
                tvMsg.setTextSize(24);
            }

            else if (sp.getInt("fontSize",0)==6)
            {
                tvMsg.setTextSize(26);
            }



        }

//        private void specifyFontSize() {
//            txtView1Size = preferences.getString("TEXT_SIZE", "20");
//            int txtSizeIntegerValue = Integer.parseInt(txtView1Size);
//            tvMsg.setTextSize(txtSizeIntegerValue);
//        }

        private void Font(){
            SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());


            if(sp.getInt("font", 0)==0)
            {


            }
            else if(sp.getInt("font", 0)==1)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/a.otf");
                tvMsg.setTypeface(tf);
            }

            else if(sp.getInt("font", 0)==2)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ab.otf");
                tvMsg.setTypeface(tf);
            }

            else if(sp.getInt("font", 0)==3)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ac.otf");
                tvMsg.setTypeface(tf);
            }

            else if(sp.getInt("font", 0)==4)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ad.otf");
                tvMsg.setTypeface(tf);
            }

            else if(sp.getInt("font", 0)==5)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ae.otf");
                tvMsg.setTypeface(tf);
            }

            else if(sp.getInt("font", 0)==6)
            {
                tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ag.otf");
                tvMsg.setTypeface(tf);
            }

        }

        @Override
        public void onStart() {
            super.onStart();
            specifyFontFamilyFromList();
            specifyFontSize();

        }

        @Override
        public void onStop() {

            super.onStop();
            specifyFontFamilyFromList();
            specifyFontSize();
        }

        @Override
        public void onDestroy() {
            if (this.adView != null) {
                this.adView.destroy();
            }
            super.onDestroy();
            specifyFontFamilyFromList();
            specifyFontSize();
        }


        ///////////////////////////
        @Override
        public void onResume() {
            if (this.adView != null) {
                this.adView.resume();
            }
            super.onResume();
            specifyFontFamilyFromList();
            specifyFontSize();
            Font();

        }

        @Override
        public void onPause() {
            if (this.adView != null) {
                this.adView.pause();
            }
            super.onPause();
            specifyFontFamilyFromList();
            specifyFontSize();

        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_pager_doaa, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_share) {
                Utils.IntenteShare(getActivity(), "أقوال وحكم", "أقوال وحكم", tvMsg.getText().toString());
                return true;
            } else if (id == R.id.action_copy) {
                String stringYouExtracted = tvMsg.getText().toString();
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                    clipboard.setText(stringYouExtracted);
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData
                            .newPlainText(stringYouExtracted, stringYouExtracted);
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(getActivity(), "تم نسخ النص", Toast.LENGTH_SHORT).show();


            }
            return super.onOptionsItemSelected(item);
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Msg m=(Msg) msg_list.get(position);

            return PlaceholderFragment.newInstance(position + 1,m.getCon_Name(), m);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return msg_list.size();
        }
    }
}
