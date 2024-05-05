package com.example.demodata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "STUDENTS.DB";
    static final int VERSION = 1;

    static final String TABLE_NAME = "STUDENT";

    static final String ROLL_NO = "roll_no";
    static final String STUD_NAME = "stud_name";
    static final String STUD_AGE = "stud_age";
    static final String STUD_CLASS = "stud_class";

    static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "( "
            +ROLL_NO+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +STUD_NAME+ " TEXT NOT NULL,"
            +STUD_AGE+ " INTEGER NOT NULL,"
            +STUD_CLASS+ " TEXT NOT NULL);";
    public DbHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }
}
