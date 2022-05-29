package com.example.lavaro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ABOUT = "about";
    public static final String KEY_CITY = "city";
    public static final String KEY_WORKEXP = "workexp";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_EDUCATION_PLACE = "education_place";
    public static final String KEY_EDUCATION = "education";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + KEY_CITY + " text," + KEY_NAME + " text," + KEY_SURNAME + " text," + KEY_MAIL + " text," + KEY_PHONE + " text,"  + KEY_EDUCATION_PLACE  + " text,"  + KEY_EDUCATION  + " text,"  + KEY_WORKEXP + " text," + KEY_ABOUT + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}