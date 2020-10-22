package com.sarrawi.hekam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Msg implements Parcelable {
   private int Con_id;
   private String Con_Name;
   private int Fav;
   private int ID_Categry;


    public Msg(String Con_Name,int ID_Categry) {
        this.Con_Name = Con_Name;
        this.ID_Categry = ID_Categry;
    }

    public Msg(String Con_Name,int ID_Categry,int Fav) {
        this.Con_Name = Con_Name;
        this.ID_Categry = ID_Categry;
        this.Fav = Fav;
    }


    public Msg()
    {

    }

    public int getCon_id() {
        return Con_id;
    }

    public void setCon_id(int con_id) {
        Con_id = con_id;
    }

    public String getCon_Name() {
        return Con_Name;
    }

    public void setCon_Name(String con_Name) {
        Con_Name = con_Name;
    }

    public int getFav() {
        return Fav;
    }

    public void setFav(int fav) {
        Fav = fav;
    }

    public int getID_Categry() {
        return ID_Categry;
    }

    public void setID_Categry(int ID_Categry) {
        this.ID_Categry = ID_Categry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
