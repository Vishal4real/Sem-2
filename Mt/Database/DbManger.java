package com.example.databasedemo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLDataException;

public class DbManger {
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DbManger(Context ctx){
        context = ctx;
    }

    public DbManger open() throws SQLDataException {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String username,String password){
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.USER_NAME,username);
        cv.put(DbHelper.USER_PASSWORD,password);
        db.insert(DbHelper.CREATE_TABLE,null,cv);
    }

    public Cursor fetch(){
        String [] columns = new String[]{DbHelper.USER_ID,DbHelper.USER_NAME,DbHelper.USER_PASSWORD};
        Cursor cursor = db.query(DbHelper.CREATE_TABLE,columns,null,null,null,null,null);
        if(cursor!= null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int id, String username, String password){
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.USER_NAME,username);
        cv.put(DbHelper.USER_PASSWORD,password);
        int ret = db.update(DbHelper.DATABASE_TABLE, cv,DbHelper.USER_ID +"="+ id,null);
        return ret;
    }

    public void delete(int id){
        db.delete(DbHelper.DATABASE_TABLE,DbHelper.USER_ID+"="+id,null);
    }
}
