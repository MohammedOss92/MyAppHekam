package com.sarrawi.hekam.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.v4.app.FragmentActivity;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sarrawi.hekam.ActivityText;
import com.sarrawi.hekam.PagerMessage;
import com.sarrawi.hekam.Pager_Amthal;
import com.sarrawi.hekam.R;
import com.sarrawi.hekam.Utils.Utils;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Amthal;
import com.sarrawi.hekam.model.Msg;
import com.sarrawi.hekam.model.MsgTypes;

import java.util.List;
import java.util.Random;

public class Amthal_Adapter extends RecyclerView.Adapter<Amthal_Adapter.MyViewHolder> {
    private List<Amthal> amthal_list;
    Context con;
    private int fontSize;
    private Typeface font;

    public Amthal_Adapter(List<Amthal> amthal_list, Context context,Typeface fontFamily, int fontSize) {
        this.con = context;
        this.amthal_list = amthal_list;
        this.font = fontFamily;
        this.fontSize = fontSize;
    }

    @NonNull
    @Override
    public Amthal_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.amthal_design, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Amthal_Adapter.MyViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        final Amthal m = amthal_list.get(position);
        DatabaseHelper s = new DatabaseHelper(con);
        holder.tv_Name.setText(m.getAmthal());
        holder.tv_Name.setTypeface(font);
        holder.tv_Name.setTextSize(fontSize);
        holder.tv_Name.setTextColor(con.getResources().getColor(R.color.colorRed));

        if ((position% 2) == 0) {
            // number is even
        } else {
            // number itv_Names odd
            holder.tv_Name.setTextColor(con.getResources().getColor(R.color.a));
        }

        final  DatabaseHelper d=new DatabaseHelper(con);
        if (d.getIFAmtIsFav(m) == 0) {
            holder.img_fav.setImageResource(R.mipmap.nf);

        } else {
            holder.img_fav.setImageResource(R.mipmap.f);
        }

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (d.getIFAmtIsFav(m) == 0) {
                    holder.img_fav.setImageResource(R.mipmap.f);
                    d.changeFavs(m, 1);
                    Toast.makeText(con, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                } else {
                    holder.img_fav.setImageResource(R.mipmap.nf);
                    d.changeFavs(m, 0);
                    Toast.makeText(con, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                }
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con, Pager_Amthal.class);

                final Amthal m=amthal_list.get(position);

                i.putExtra("pos",position);
                i.putExtra("msgID",m.getID());
                //i.putExtra("filter",m.getMsg_Filter());
//                i.putExtra("sourceIsFav",true);

                con.startActivity(i);
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


        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringYouExtracted = holder.tv_Name.getText().toString();
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(stringYouExtracted);
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) con.getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData
                            .newPlainText(stringYouExtracted, stringYouExtracted);
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(con, "تم نسخ النص", Toast.LENGTH_SHORT).show();
            }
        });


        ///////////////
    }

    @Override
    public int getItemCount() {
        return amthal_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Name;
        ImageView img_fav;
        CardView relativeLayout;
        ImageView share;
        ImageView copy;
        ImageView shared;
        public MyViewHolder(@NonNull View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.txt_msgs);
            relativeLayout = (CardView) view.findViewById(R.id.card_view_am);
            img_fav =(ImageView) view.findViewById(R.id.img_favs);
            share = (ImageView) view.findViewById(R.id.sharesam);
            copy = (ImageView) view.findViewById(R.id.copysam);
            shared = (ImageView) view.findViewById(R.id.sharedsam);
        }
    }

    public void setFont(Typeface font) {
        this.font = font;
    }

    public void setTextSizes(int textSize) {
        this.fontSize = textSize;
    }
}