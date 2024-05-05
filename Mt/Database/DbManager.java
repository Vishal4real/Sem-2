package com.example.demodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {
    DbHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public DbManager(Context ctx){
        context = ctx;
    }
    public DbManager open(){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void insert(String name,int age,String stud_class){
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.STUD_NAME,name);
        cv.put(DbHelper.STUD_AGE,age);
        cv.put(DbHelper.STUD_CLASS,stud_class);
        db.insert(DbHelper.TABLE_NAME,null,cv);
    }
    public int update(int roll,String name,int age,String stud_class){
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.STUD_NAME,name);
        cv.put(DbHelper.STUD_AGE,age);
        cv.put(DbHelper.STUD_CLASS,stud_class);
       return db.update(DbHelper.TABLE_NAME,cv, DbHelper.ROLL_NO+"="+roll ,null);
    }

    public Cursor fetch(){
        String q = "SELECT * FROM "+DbHelper.TABLE_NAME+";";
        String[] args = {DbHelper.ROLL_NO,DbHelper.STUD_NAME};
        return db.rawQuery(q, args);
    }

    public void delete(int roll) {
        db.delete(DbHelper.TABLE_NAME, DbHelper.ROLL_NO + "=" + roll, null);
    }
    public void close(){
        db.close();
    }
}
