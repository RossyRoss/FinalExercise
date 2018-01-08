package com.example.pc_user.finalexercise;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class AddActivity extends AppCompatActivity {
    EditText id, fname, lname, mname, course, year;
    String idText, fnameText, lnameText, mnameText, courseText, yearText,checkId;
    Button add, clear;
    SQLiteDatabase dbase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        refId();
        DBHelper helper = new DBHelper(getApplication(), "schoolDB", 1);
        dbase = helper.getWritableDatabase();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idText = id.getText().toString();
                fnameText = fname.getText().toString();
                lnameText = lname.getText().toString();
                mnameText = mname.getText().toString();
                courseText = course.getText().toString();
                yearText = year.getText().toString();

                addStudent();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                fname.setText("");
                lname.setText("");
                mname.setText("");
                course.setText("");
                year.setText("");
            }
        });
    }

    public boolean checkStudent(){
        Cursor c;
        String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};
        checkId = id.getText().toString();
        c = dbase.query("StudentFile",fields, "IdNumber = '" + checkId + "'", null, null, null, null, null);

        c.moveToFirst();

        if(c.isAfterLast()) return true;
        return false;
    }
    public void addStudent() {
        if(idText.equals("") || lnameText.equals("") || fnameText.equals("") || mnameText.equals("") || courseText.equals("") || yearText.equals("")){
            Toast.makeText(getApplication(), "Please input missing fields!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (checkStudent()) {
                ContentValues data = new ContentValues();
                data.put("IdNumber", idText);
                data.put("LastName", lnameText);
                data.put("FirstName", fnameText);
                data.put("MiddleInitial", mnameText);
                data.put("Course", courseText);
                data.put("Year", yearText);

                dbase.insert("StudentFile", null, data);
                Toast.makeText(getApplication(), "SUCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplication(), "Already exist!", Toast.LENGTH_SHORT).show();
                id.setText("");
                fname.setText("");
                lname.setText("");
                mname.setText("");
                course.setText("");
                year.setText("");
            }
        }

    }

    public void refId() {
        id = (EditText) findViewById(R.id.IdNumId);
        fname = (EditText) findViewById(R.id.firstNameRegId);
        lname = (EditText) findViewById(R.id.lastNameRegId);
        mname = (EditText) findViewById(R.id.MidInitId);
        course = (EditText) findViewById(R.id.courseId);
        year = (EditText) findViewById(R.id.yearId);

        add = (Button) findViewById(R.id.addBtn);
        clear = (Button) findViewById(R.id.clearBtn);
    }
}