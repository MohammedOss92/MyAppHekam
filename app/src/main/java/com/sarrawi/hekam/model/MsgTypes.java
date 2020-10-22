package com.sarrawi.hekam.model;

public class MsgTypes {
    int ID;
    private String name;
    private int fav;
    private String namese, genre, year;

    public MsgTypes(String name) {
        this.name = name;
    }





    public MsgTypes ()
    {

    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
