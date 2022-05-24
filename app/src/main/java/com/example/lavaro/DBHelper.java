package com.example.lavaro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


// я тут ничего не понимал , сейчас тоже , остальное сами сделаете)


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "workers" ;
    public static final String TABLE_WORKERS = "workers" ;

    public static final String KEY_ID = "_id" ;
    public static final String KEY_NAME = "name" ;
    public static final String KEY_SURNAME = "surname" ;
    public static final String KEY_PHONE = "phone" ;
    public static final String KEY_MAIL = "mail" ;
    public static final String KEY_PASSWORD = "password" ;


    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WORKERS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_SURNAME + " text," + KEY_MAIL + " text," + KEY_PHONE + " text," + KEY_PASSWORD +" text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_WORKERS);

        onCreate(db);

    }
}
