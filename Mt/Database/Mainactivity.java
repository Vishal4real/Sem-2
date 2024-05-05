package com.example.demodata;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText stud_name,stud_roll,stud_age,stud_class;
    Button insert,update,delete,view;
    DbManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        stud_age = findViewById(R.id.age);
        stud_class =findViewById(R.id.stud_class);
        stud_name = findViewById(R.id.name);
        stud_roll = findViewById(R.id.rollno);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);

        insert.setOnClickListener(this);
        update.setOnClickListener(this);delete.setOnClickListener(this);
        view.setOnClickListener(this);

        dbManager = new DbManager(this);
        dbManager.open();

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.insert){
            dbManager.insert(stud_name.getText().toString(),parseInt(stud_age.getText().toString()),stud_class.getText().toString());
            Log.i("STUD_DATABASE","INSERTED");
        }
        if(v.getId()==R.id.delete){
            dbManager.delete(parseInt(stud_roll.getText().toString()));
            Log.i("STUD_DATABASE","DELETED");
        }
        if(v.getId()==R.id.update){
            dbManager.update(parseInt(stud_roll.getText().toString()),stud_name.getText().toString(),parseInt(stud_age.getText().toString()),stud_class.getText().toString());
            Log.i("STUD_DATABASE","UPDATED");
        }

        if(v.getId()==R.id.view){
            Cursor cursor =  dbManager.fetch();
            if(cursor!=null || cursor.moveToFirst()){
                do{
                    @SuppressLint("Range") String roll = cursor.getString(cursor.getColumnIndex(DbHelper.ROLL_NO));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DbHelper.STUD_NAME));
                    Log.i("STUD_DATABASE",roll+" "+ name);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}