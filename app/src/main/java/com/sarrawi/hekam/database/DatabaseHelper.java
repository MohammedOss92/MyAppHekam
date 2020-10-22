package com.sarrawi.hekam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sarrawi.hekam.model.Amthal;
import com.sarrawi.hekam.model.Msg;
import com.sarrawi.hekam.model.MsgTypes;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "HekamDB.db";
    public static final String DBLOCATION = "/data/data/com.sarrawi.hekam/databases/";
    public static Context mContext;
    private SQLiteDatabase mDatabase;
    private static DatabaseHelper sInstance;


    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }



    public ArrayList<MsgTypes> getMessageTypes() {

        ArrayList<MsgTypes> contactList = new ArrayList<MsgTypes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + "hekam_Types";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("cursor", cursor.toString());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MsgTypes row = new MsgTypes();

                row.setID(Integer.parseInt(cursor.getString(0)));
                row.setName(cursor.getString(1));

                // Adding contact to list
                contactList.add(row);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return contactList;
    }

    public ArrayList<Amthal> getAmthal() {

            ArrayList<Amthal> contactList = new ArrayList<Amthal>();
            // Select All Query
            String selectQuery = "SELECT * FROM " + "hAmthal";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            Log.e("cursor", cursor.toString());
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Amthal row = new Amthal();

                    row.setID(Integer.parseInt(cursor.getString(0)));
                    row.setAmthal(cursor.getString(1));

                    // Adding contact to list
                    contactList.add(row);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            // return contact list
            return contactList;
        }
    public ArrayList<Msg> getAllMsg(int typeID) {

        ArrayList<Msg> contactList = new ArrayList<Msg>();
        // Select All Query
        String selectQuery = "SELECT * FROM hName where ID_Type='" + typeID + "' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("cursor", cursor.toString());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Msg row = new Msg();
                row.setCon_id(Integer.parseInt(cursor.getString(0)));
                row.setID_Categry(Integer.parseInt(cursor.getString(2)));
                row.setCon_Name(cursor.getString(1));


                // Adding contact to list
                contactList.add(row);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return contactList;
    }

    public ArrayList<Msg> getAllMsgnotID() {

        ArrayList<Msg> contactList = new ArrayList<Msg>();
        // Select All Query
        String selectQuery = "SELECT * FROM hName where ID_Type ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("cursor", cursor.toString());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Msg row = new Msg();
                row.setCon_id(Integer.parseInt(cursor.getString(0)));
                row.setID_Categry(Integer.parseInt(cursor.getString(2)));
                row.setCon_Name(cursor.getString(1));


                // Adding contact to list
                contactList.add(row);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return contactList;
    }


    public String getMsgTitleByTitleID(int msgType) {

        String result = "";
        SQLiteDatabase db = getReadableDatabase();

        Cursor countCursor = db.rawQuery("SELECT hTypes from hekam_Types where ID=" + msgType, null);
        if (countCursor.moveToFirst()) {
            result = countCursor.getString(0);
        }
        countCursor.close();
        db.close();

        return result;
    }

    public List<Msg> getMessagesNotOrdered(int typeID) {
        Msg u;

        List<Msg> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT *   FROM hName   where ID_Type='" + typeID + "' ", null);

        if (c.moveToFirst()) {

            do {
                u = new Msg();
                u.setCon_id(c.getInt(0));
                u.setCon_Name(c.getString(1));
                u.setID_Categry(c.getInt(2));
                u.setFav(c.getInt(3));
                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }

    public List<Amthal> getAmthalNotOrdered() {
        Amthal u;

        List<Amthal> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT *   FROM hAmthal    ", null);

        if (c.moveToFirst()) {

            do {
                u = new Amthal();
                u.setID(c.getInt(0));
                u.setAmthal(c.getString(1));
                u.setFav(c.getInt(2));
                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }




//    public ArrayList<Msg> getFaVourit() {
//        Msg m;
//        ArrayList<Msg> Data = new ArrayList();
//        SQLiteDatabase db = getReadableDatabase();
////        db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 0);
//        String SelectQuery = "Select * from Messages where FAV= 1";
//        Cursor cursor = db.rawQuery(SelectQuery, null);
//        Log.e("cursor", cursor.toString());
////        Cursor cursor = db.rawQuery("Select * from vmsg where FAV= 1", null);
//        if (cursor.moveToFirst()) {
//
//            do {
//                Msg row = new Msg();
//                row.setID_Msg(Integer.parseInt(cursor.getString(0)));
//                row.setID_Type(Integer.parseInt(cursor.getString(2)));
//                row.setMsg_Name(cursor.getString(1));
//                row.setFav(Integer.parseInt(cursor.getString(3)));
//
//
//                Data.add(row);
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return Data;
//    }
    public void favorite(int ID,String Name,int Type,int Fav) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        values.put("IDMsg",ID);
        values.put("MessageName",Name);
        values.put("ID_Type",Type);
        values.put("Fav",Fav);
        // Inserting Row
        db.update("Messages", values, "IDMsg=" + ID, null);
//        db.update("Messages", values, "ID_Type=" + Type, null);

        db.close();
        // Closing database connection
    }


    public int getIFMsgIsFav(Msg m) {
        int result = 0;
        SQLiteDatabase db = getWritableDatabase();
        Cursor countCursor = db.rawQuery("SELECT Fav  from hName where ID_h=" + m.getCon_id(), null);
        if (countCursor.moveToFirst()) {
            result = countCursor.getInt(0);
        }
        db.close();
        return result;
    }

    public int getIFAmtIsFav(Amthal m) {
        int result = 0;
        SQLiteDatabase db = getWritableDatabase();
        Cursor countCursor = db.rawQuery("SELECT Fav  from hAmthal where ID_Am=" + m.getID(), null);
        if (countCursor.moveToFirst()) {
            result = countCursor.getInt(0);
        }
        db.close();
        return result;
    }

    public List<Msg> getFavMessages() {
        Msg u;

        List<Msg> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT *   FROM hName   where Fav=1", null);

        if (c.moveToFirst()) {

            do {
                u = new Msg();
                u.setCon_id(c.getInt(0));
                u.setCon_Name(c.getString(1));
                u.setID_Categry(c.getInt(2));
                u.setFav(c.getInt(3));

                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }
    public List<Amthal> getFavAmthal() {
        Amthal u;

        List<Amthal> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT *   FROM hAmthal   where Fav=1", null);

        if (c.moveToFirst()) {

            do {
                u = new Amthal();
                u.setID(c.getInt(0));
                u.setAmthal(c.getString(1));
                u.setFav(c.getInt(2));

                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }



    public void changeFav(Msg msg, int intFav) {


        SQLiteDatabase db = getWritableDatabase();
        String sql = "select max(Fav) from hName";
        int intMaxOrderOfFav = 0;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) ;
        {
            intMaxOrderOfFav = c.getInt(0);
        }
        intMaxOrderOfFav = intMaxOrderOfFav + 1;
        if (intFav == 0) {
            sql = "update hName set Fav=" + intFav + " ,Fav=0 where ID_h=" + msg.getCon_id();
            db.execSQL(sql);
            sql = "delete from  Favs where ID_h=" + msg.getCon_id();
//            db.execSQL(sql);
        } else {

            sql = "update hName set Fav=" + intFav + " ,Fav=1 where ID_h=" + msg.getCon_id();
            db.execSQL(sql);

//            sql = "insert into favs values(" + msg.getMsgID() + ")";
//            db.execSQL(sql);
        }

        c.close();
        db.close();
    }

    public void changeFavs(Amthal msg, int intFav) {


            SQLiteDatabase db = getWritableDatabase();
            String sql = "select max(Fav) from hAmthal";
            int intMaxOrderOfFav = 0;
            Cursor c = db.rawQuery(sql, null);
            if (c.moveToFirst()) ;
            {
                intMaxOrderOfFav = c.getInt(0);
            }
            intMaxOrderOfFav = intMaxOrderOfFav + 1;
            if (intFav == 0) {
                sql = "update hAmthal set Fav=" + intFav + " ,Fav=0 where ID_Am=" + msg.getID();
                db.execSQL(sql);
                sql = "delete from  Favs where ID_h=" + msg.getID();
    //            db.execSQL(sql);
            } else {

                sql = "update hAmthal set Fav=" + intFav + " ,Fav=1 where ID_Am=" + msg.getID();
                db.execSQL(sql);

    //            sql = "insert into favs values(" + msg.getMsgID() + ")";
    //            db.execSQL(sql);
            }

            c.close();
            db.close();
        }



//
//    public ArrayList<Product> getAllPrayer(String text) {
//        text = "%" + text + "%";
//        ArrayList<Product> contactList = new ArrayList<Product>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + "azkar_type" + " WHERE Name_filter  LIKE '" + text + "'";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        Log.e("cursor", cursor.toString());
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Product row = new Product();
//             /*   Log.e (" cursor.getString (1)", cursor.getString (1) + "");// send
//                Log.e (" cursor.getString type", cursor.getString (2) + "");// type
//                Log.e (" cursor.getString date", cursor.getString (3) + "");// date
//                Log.e (" cursor.getString (4)", cursor.getString (4) + "");// time
//*/
//                row.setId(Integer.parseInt(cursor.getString(0)));
//                row.setName(cursor.getString(1));
//                row.setName_filter(cursor.getString(3));
//                row.setFav(Integer.parseInt(cursor.getString(2)));
//
//                // Adding contact to list
//                contactList.add(row);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        // return contact list
//        return contactList;
//    }
//
//    public String getMsgTitleByTitleID(int msgType) {
//
//        String result = "";
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor countCursor = db.rawQuery("SELECT Name from azkar_type where ID=" + msgType, null);
//        if (countCursor.moveToFirst()) {
//            result = countCursor.getString(0);
//        }
//        countCursor.close();
//        db.close();
//
//        return result;
//    }

    public static DatabaseHelper getInstance(Context contexts) {
        mContext= contexts;
        if (sInstance == null) {
            sInstance = new DatabaseHelper(contexts.getApplicationContext());
        }
        return sInstance;
    }


}
