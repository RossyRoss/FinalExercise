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

public class UpdateActivity extends AppCompatActivity {
    EditText id, fname, lname, mname, course, year;
    String idText, fnameText, lnameText, mnameText, courseText, yearText;
    SQLiteDatabase dbase;
    Button update, clear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        DBHelper helper = new DBHelper(getApplication(), "schoolDB", 1);
        dbase = helper.getWritableDatabase();
        refId();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update.getText().toString().equalsIgnoreCase("Search")){
                    Cursor c;
                    String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};
                    idText = id.getText().toString();
                    c = dbase.query("StudentFile",fields, "IdNumber = '" + idText + "'", null, null, null, null, null);
                    c.moveToFirst();

                    if(!c.isAfterLast()){
                        fname.setText(c.getString(1));
                        lname.setText(c.getString(2));
                        mname.setText(c.getString(3));
                        course.setText(c.getString(4));
                        year.setText(c.getString(5));

                        id.setEnabled(false);
                        fname.setEnabled(true);
                        lname.setEnabled(true);
                        mname.setEnabled(true);
                        course.setEnabled(true);
                        year.setEnabled(true);

                        update.setText("Update");
                    }
                    else{
                        Toast.makeText(getApplication(), "ID Number not found!", Toast.LENGTH_SHORT).show();
                    }
                    c.close();
                }
                else{
                    idText = id.getText().toString();
                    fnameText = fname.getText().toString();
                    lnameText = lname.getText().toString();
                    mnameText = mname.getText().toString();
                    courseText = course.getText().toString();
                    yearText = year.getText().toString();

                    ContentValues data = new ContentValues();
                    data.put("IdNumber", idText);
                    data.put("LastName", lnameText);
                    data.put("FirstName", fnameText);
                    data.put("MiddleInitial", mnameText);
                    data.put("Course", courseText);
                    data.put("Year", yearText);

                    dbase.update("StudentFile",data,"IdNumber = '" + idText +"'", null);
                    id.setEnabled(true);
                    fname.setEnabled(false);
                    lname.setEnabled(false);
                    mname.setEnabled(false);
                    course.setEnabled(false);
                    year.setEnabled(false);

                    update.setText("search");
                    Toast.makeText(getApplication(), "SUCCESSFULLY UPDATED!", Toast.LENGTH_SHORT).show();
                }
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

    private void refId() {
        id = (EditText) findViewById(R.id.IdNumId);
        fname = (EditText) findViewById(R.id.firstNameRegId);
        lname = (EditText) findViewById(R.id.lastNameRegId);
        mname = (EditText) findViewById(R.id.MidInitId);
        course = (EditText) findViewById(R.id.courseId);
        year = (EditText) findViewById(R.id.yearId);

        update = (Button) findViewById(R.id.updateBtn);
        clear = (Button) findViewById(R.id.clearBtn);
    }
}