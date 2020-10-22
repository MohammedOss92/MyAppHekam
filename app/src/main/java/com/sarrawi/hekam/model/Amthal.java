package com.sarrawi.hekam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Amthal implements Parcelable {
    private int ID;
    private String Amthal;
    private int Fav;

    public Amthal(int ID, String amthal, int fav) {
        this.ID = ID;
        Amthal = amthal;
        Fav = fav;
    }

    public Amthal(int ID, String amthal) {
        this.ID = ID;
        Amthal = amthal;
    }

    public Amthal() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAmthal() {
        return Amthal;
    }

    public void setAmthal(String amthal) {
        Amthal = amthal;
    }

    public int getFav() {
        return Fav;
    }

    public void setFav(int fav) {
        Fav = fav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
