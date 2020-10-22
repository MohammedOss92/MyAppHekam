package com.sarrawi.hekam.adapters;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.sarrawi.hekam.ActivityText;
import com.sarrawi.hekam.MainActivity;
import com.sarrawi.hekam.PagerMessage;
import com.sarrawi.hekam.R;
import com.sarrawi.hekam.SetActivity;
import com.sarrawi.hekam.Utils.Utils;
import com.sarrawi.hekam.database.DataBase;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Msg;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MyViewHolder> {
    private List<Msg> msg_list;
    Context con;
    private int fontSize;
    private Typeface font;
    private int lastPosition = -1;
    int clickcount;
    InterstitialAd mInterstitialAd;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_Name;
        ImageView img_fav,img_share,img_copy,img_shared;
        CardView relativeLayout;
        ImageView share;
        ImageView copy;
        ImageView shared;


        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tvTitle);
            tv_Name = (TextView) view.findViewById(R.id.txt_msg);
            relativeLayout = (CardView) view.findViewById(R.id.card_view_t);
            img_fav =(ImageView) view.findViewById(R.id.img_fav);

            share = (ImageView) view.findViewById(R.id.share);
            copy = (ImageView) view.findViewById(R.id.copy);
            shared = (ImageView) view.findViewById(R.id.shared);
        }    }
    public MsgAdapter(List<Msg> msg_list, Context context, Typeface fontFamily, int fontSize) {
        this.con = context;
        this.msg_list = msg_list;
        this.font = fontFamily;
        this.fontSize = fontSize;
    }
    @NonNull
    @Override
    public MsgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.msg_design, viewGroup, false);
        return new MsgAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MsgAdapter.MyViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        final Msg m = msg_list.get(position);
        DatabaseHelper s = new DatabaseHelper(con);
        int titleId = m.getID_Categry();
        String titleDesc = s.getMsgTitleByTitleID(titleId);
        holder.tv_title.setText(titleDesc);
        holder.tv_Name.setText(m.getCon_Name());
        holder.tv_Name.setTypeface(font);
        holder.tv_title.setTypeface(font);
        holder.tv_Name.setTextSize(fontSize);
        holder.tv_title.setTextColor(con.getResources().getColor(R.color.a));
        holder.tv_Name.setTextColor(con.getResources().getColor(R.color.colorRed));
        holder.tv_title.setTextSize(14);



//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(con, R.anim.anim_translate);
//            holder.relativeLayout.startAnimation(animation);
//            lastPosition = position;
//        }


//        holder.img_fav.setAnimation(AnimationUtils.loadAnimation(con,R.anim.anim_rotate));
//        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(con,R.anim.anim_alpha));


        if ((position% 2) == 0) {
            // number is even
        } else {
            // number is odd
            holder.tv_Name.setTextColor(con.getResources().getColor(R.color.a));
            holder.tv_title.setTextColor(con.getResources().getColor(R.color.colorRed));
        }


        final  DatabaseHelper d=new DatabaseHelper(con);
        if (d.getIFMsgIsFav(m) == 0) {
            holder.img_fav.setImageResource(R.mipmap.nf);

        } else {
            holder.img_fav.setImageResource(R.mipmap.f);
        }

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.img_fav.setAnimation(AnimationUtils.loadAnimation(con,R.anim.anim_scale));

                if (d.getIFMsgIsFav(m) == 0) {
                    holder.img_fav.setImageResource(R.mipmap.f);
                    d.changeFav(m, 1);
                    Toast.makeText(con, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                    Animation animation = AnimationUtils.loadAnimation(con, android.R.anim.slide_out_right);
                    holder.img_fav.startAnimation(animation);
                } else {
                    holder.img_fav.setImageResource(R.mipmap.nf);
                    d.changeFav(m, 0);
                    Toast.makeText(con, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                    Animation animation = AnimationUtils.loadAnimation(con, android.R.anim.slide_out_right);
                    holder.img_fav.startAnimation(animation);
                }
                Animation animation = AnimationUtils.loadAnimation(con, android.R.anim.slide_out_right);
                holder.img_fav.startAnimation(animation);
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con, PagerMessage.class);

                final Msg m=msg_list.get(position);

                i.putExtra("titleID",m.getID_Categry());
                i.putExtra("pos",position);
                i.putExtra("msgID",m.getCon_id());
                //i.putExtra("filter",m.getMsg_Filter());
//                i.putExtra("sourceIsFav",true);

                con.startActivity(i);
            }
        });

//        setAnimation(holder.relativeLayout, position);

        ///////////////

        holder.shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                String text = holder.tv_Name.getText().toString();
                waIntent.setPackage("com.whatsapp");
                if (waIntent != null) {
                    waIntent.putExtra(Intent.EXTRA_TEXT, text);//
                    con.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else {
                    Toast.makeText(con, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder popDialog = new AlertDialog.Builder(con);
                popDialog.setTitle("هل ترغب بتعديل النص");

                popDialog.setNegativeButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent i = new Intent(con, ActivityText.class);
                        String data = holder.tv_Name.getText().toString();
                        i.putExtra("na", holder.tv_Name.getText().toString());
                        con.startActivity(i);
                    }
                });

                popDialog.setPositiveButton("لا", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Utils.IntenteShare(con, "حكم وأقوال", "حكم وأقوال", holder.tv_Name.getText().toString());
                    }
                });
                //Utils.IntenteShare(c, "مسجاتي", "مسجاتي", holder.tvMsg.getText().toString());

                popDialog.show();
            }
        });
        prepareAd();
        ShowAds();
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringYouExtracted = holder.tv_Name.getText().toString();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                    ClipboardManager clipboard = (ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(stringYouExtracted);
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData
                            .newPlainText(stringYouExtracted, stringYouExtracted);
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(con, "تم نسخ النص", Toast.LENGTH_SHORT).show();
                Animation animation = AnimationUtils.loadAnimation(con, android.R.anim.fade_out);
                holder.copy.startAnimation(animation);
//                clickcount = clickcount + 1;
//                if(clickcount == 3)
//                {
//                    ShowAds();
//                    clickcount=0;
//                }
            }
        });
        ///////////////

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(con, android.R.anim.fade_out);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
//        if(msg_list==null || msg_list.isEmpty()){
//            return 0;
//        }
//        else {
//            return msg_list.size();
//        }
        return msg_list.size();
    }

    public void setFont (Typeface font)
    {
        this.font = font;
    }

    public void setTextSizes(int textSize) {
        this.fontSize = textSize;
    }

    public void  prepareAd(){
        mInterstitialAd = new InterstitialAd(con);
        mInterstitialAd.setAdUnitId("ca-app-pub-1895204889916566/9140796492");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
            @Override
            public void onAdOpened() {
            }
            @Override
            public void onAdLeftApplication() {
            }
            @Override
            public void onAdClosed() {
            }        });    }

    public void ShowAds(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG"," Interstitial not loaded");
        }
        prepareAd();
    }

}