package com.example.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION  = 1;

    static final String DATABASE_TABLE = "USER";
    static final String USER_ID = "id";
    static final String USER_NAME = "username";
    static final String USER_PASSWORD = "password";
    static final String CREATE_TABLE = "CREATE TABLE "+DATABASE_TABLE+" ( "
            +USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +USER_NAME+" TEXT NOT NULL, "
            +USER_PASSWORD+" TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
    }
}