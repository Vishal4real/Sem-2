package com.example.databasedemo;

import static java.lang.Integer.parseInt;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity {

    EditText username, password, id;
    DbManger dbManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword);
        id = findViewById(R.id.editTextNumber);

        dbManger = new DbManger(this);

        try {
            dbManger.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnInsertedPressed(View v){
        dbManger.insert(username.getText().toString(),password.getText().toString());
    }
    public void btnUpdatedPressed(View v){
        dbManger.update(parseInt(id.getText().toString()),username.getText().toString(),password.getText().toString());
    }
    public void btnFetchPressed(View v){
        Cursor cursor = dbManger.fetch();
        if(cursor!=null){
            do{
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DbHelper.USER_NAME));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DbHelper.USER_ID));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DbHelper.USER_PASSWORD));
                Log.i("DATABASE",id+" "+username+" "+" "+password);
            }while(cursor.moveToNext());
        }
    }
    public void btnDeletePressed(View v){
        dbManger.delete(parseInt(id.getText().toString()));
    }
}