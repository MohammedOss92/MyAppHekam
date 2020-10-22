package com.sarrawi.hekam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sarrawi.hekam.model.MsgTypes;

import java.util.ArrayList;

public class SqliteClasses extends SQLiteOpenHelper {

    public static Context mContext;
    private SQLiteDatabase mDatabase;
    private static SqliteClasses sInstance;
    private static final String DATABASE_NAME = "ab12341";
    private static final int DATABASE_VERSION = 1;

    public SqliteClasses(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public SqliteClasses(Context context) {
//        super(context, "a", null, 1);
//
//    }

    public SqliteClasses(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Category = "CREATE TABLE IF NOT EXISTS Category(ID INTEGER NOT NULL PRIMARY KEY," +
                "C_Name TEXT)";
        db.execSQL(CREATE_Category);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public void openDatabase() {
//        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
//        if(mDatabase != null && mDatabase.isOpen()) {
//            return;
//        }
//        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public void closeDatabase() {
//        if(mDatabase!=null) {
//            mDatabase.close();
//        }
//    }

    public static SqliteClasses getInstance(Context contexts) {
        mContext= contexts;
        if (sInstance == null) {
            sInstance = new SqliteClasses(contexts.getApplicationContext());
        }
        return sInstance;
    }

    public void addNewRow(String ID, String C_Name) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        if (!checkIsDataAlreadyInDBorNot(db, "Category", "C_Name",C_Name)) {
            values.put("ID", ID);
            values.put("C_Name", C_Name);

            // Inserting Row
            db.insert("Category", null, values);
        }
        db.close();
        // Closing database connection
    }

    public boolean checkIsDataAlreadyInDBorNot(SQLiteDatabase sqldb, String TableName, String dbfield, String fieldValue) {
        String Query = "SELECT " + dbfield + " FROM " + TableName + " WHERE " + dbfield + " =?";
        Cursor cursor = sqldb.rawQuery(Query, new String[]{fieldValue});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public ArrayList<MsgTypes> getMsgTypes() {


        ArrayList<MsgTypes> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *   FROM Category ", null);

        if (c.moveToFirst()) {

            do {
                MsgTypes u = new MsgTypes();
                u.setID(Integer.parseInt(c.getString(0)));
                u.setName(c.getString(1));

                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }


}
