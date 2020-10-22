package com.sarrawi.hekam.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
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

import com.sarrawi.hekam.ActivityText;
import com.sarrawi.hekam.PagerMessage;
import com.sarrawi.hekam.R;
import com.sarrawi.hekam.Utils.Utils;
import com.sarrawi.hekam.database.DataBase;
import com.sarrawi.hekam.database.DatabaseHelper;
import com.sarrawi.hekam.model.Msg;

import java.util.List;

public class MsgFavAdapter extends RecyclerView.Adapter<MsgFavAdapter.MyViewHolder> {
    private List<Msg> msg_list;
    Context con;
    private int fontSize;
    private Typeface font;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMsg;
        ImageView fav;
        CardView cardview;
        TextView tvTitle;
        ImageView share;
        ImageView copy;
        ImageView shared;

        public MyViewHolder(View view) {
            super(view);
            txtMsg=(TextView)view.findViewById(R.id.txt_msg);
            fav = (ImageView)view.findViewById(R.id.img_fav);
            cardview = (CardView) view.findViewById(R.id.card_view_t);
            share = (ImageView) view.findViewById(R.id.share);
            copy = (ImageView) view.findViewById(R.id.copy);
            shared = (ImageView) view.findViewById(R.id.shared);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }


    public MsgFavAdapter(List<Msg> msg_list, Context context, Typeface fontFamily, int fontSize)
    {
        this.con = context;
        this.msg_list=msg_list;
        this.font = fontFamily;
        this.fontSize = fontSize;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_design, parent, false);


        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull final MsgFavAdapter.MyViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        final Msg m = msg_list.get(position);
        final DatabaseHelper s = new DatabaseHelper(con);
        int titleId = m.getID_Categry();
        String titleDesc = s.getMsgTitleByTitleID(titleId);
        holder.tvTitle.setText(titleDesc);
        holder.txtMsg.setText(m.getCon_Name());
        holder.tvTitle.setTypeface(font);
        holder.txtMsg.setTypeface(font);
        holder.txtMsg.setTextColor(con.getResources().getColor(R.color.a));
        holder.tvTitle.setTextColor(con.getResources().getColor(R.color.colorRed));
        holder.txtMsg.setTextSize(fontSize);
        holder.tvTitle.setTextSize(14);

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
            holder.tvTitle.setTextColor(con.getResources().getColor(R.color.a));
            holder.txtMsg.setTextColor(con.getResources().getColor(R.color.colorRed));
        }

        final  DatabaseHelper d=new DatabaseHelper(con);
        if (d.getIFMsgIsFav(m) == 0) {
            holder.fav.setImageResource(R.mipmap.nf);

        } else {
            holder.fav.setImageResource(R.mipmap.f);
        }

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (d.getIFMsgIsFav(m) == 0) {
                    holder.fav.setImageResource(R.mipmap.f);
                    d.changeFav(m, 1);
                    Toast.makeText(con, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                } else {

                    holder.fav.setImageResource(R.mipmap.nf);
                    d.changeFav(m, 0);
                    Toast.makeText(con, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                    msg_list.remove(position);
                    notifyDataSetChanged();
                }

            }
        });

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con, PagerMessage.class);

                final Msg m=msg_list.get(position);
                i.putExtra("msgID",m.getCon_id());
//                i.putExtra("origPos",m.getOrigPos());
                i.putExtra("titleID",m.getID_Categry());
                i.putExtra("sourceIsFav",true);
                i.putExtra("pos",position);
                con.startActivity(i);
            }
        });

        holder.shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                String text = holder.txtMsg.getText().toString();
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
                        String data = holder.txtMsg.getText().toString();
                        i.putExtra("na", holder.txtMsg.getText().toString());
                        con.startActivity(i);
                    }
                });

                popDialog.setPositiveButton("لا", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Utils.IntenteShare(con, "مسجاتي", "مسجاتي", holder.txtMsg.getText().toString());
                    }
                });
                //Utils.IntenteShare(c, "مسجاتي", "مسجاتي", holder.tvMsg.getText().toString());

                popDialog.show();
            }
        });


        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringYouExtracted = holder.txtMsg.getText().toString();
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




    }



    @Override
    public int getItemCount() {
        if(msg_list==null || msg_list.isEmpty()){
            return 0;
        }
        else {
            return msg_list.size();
        }
    }
    public void setFont(Typeface font) {
        this.font = font;
    }
    public void setTextSizes(int textSize) {
        this.fontSize = textSize;
    }


}