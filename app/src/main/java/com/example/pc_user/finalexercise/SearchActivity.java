package com.example.pc_user.finalexercise;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class SearchActivity extends AppCompatActivity {
    SQLiteDatabase dbase;
    Button searchID,searchCourse;
    TableLayout tbl;
    EditText id,course;
    String IdText, courseText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        DBHelper helper = new DBHelper(getApplication(),"schoolDB",1);
        dbase = helper.getReadableDatabase();
        refId();
        searchID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IdText = id.getText().toString();
                if(IdText.equals("")){
                    Toast.makeText(getApplication(), "Please input id field.", Toast.LENGTH_SHORT).show();
                }
                else {
                    tbl.removeAllViews();
                    Cursor c;
                    String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};
                    c = dbase.query("StudentFile", fields, "IdNumber = '" + IdText + "'", null, null, null, null, null);
                    display(c);
                }

            }
        });

        searchCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseText = course.getText().toString();
                if(courseText.equals("")){
                    Toast.makeText(getApplication(), "Please input course field.", Toast.LENGTH_SHORT).show();
                }
                else {
                    tbl.removeAllViews();
                    Cursor c;
                    String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};
                    c = dbase.query("StudentFile", fields, "Course = '" + courseText + "'", null, null, null, null, null);
                    display(c);
                }
            }
        });
    }

    public void display(Cursor c){
        c.moveToFirst();

        if(!c.isAfterLast()){
            TableRow trow = new TableRow(getApplication());

            TextView idd = new TextView(getApplication());
            idd.setText("" + c.getString(0));
            idd.setTextSize(15);
            idd.setGravity(Gravity.CENTER);
            idd.setTextColor(Color.BLACK);
            idd.setPadding(10,20,10,10);
            trow.addView(idd);

            TextView firstName = new TextView(getApplication());
            firstName.setText("" + c.getString(2));
            firstName.setTextSize(15);
            firstName.setGravity(Gravity.CENTER);
            firstName.setTextColor(Color.BLACK);
            firstName.setPadding(20,30,20,20);
            trow.addView(firstName);

            TextView lastName = new TextView(getApplication());
            lastName.setText("" + c.getString(1));
            lastName.setTextSize(15);
            lastName.setGravity(Gravity.CENTER);
            lastName.setTextColor(Color.BLACK);
            lastName.setPadding(20,30,20,20);
            trow.addView(lastName);

            TextView middleInitial = new TextView(getApplication());
            middleInitial.setText("" + c.getString(3));
            middleInitial.setTextSize(15);
            middleInitial.setGravity(Gravity.CENTER);
            middleInitial.setTextColor(Color.BLACK);
            middleInitial.setPadding(20,30,20,20);
            trow.addView(middleInitial);

            TextView Course = new TextView(getApplication());
            Course.setText("" + c.getString(4));
            Course.setTextSize(15);
            Course.setGravity(Gravity.CENTER);
            Course.setTextColor(Color.BLACK);
            Course.setPadding(20,30,20,20);
            trow.addView(Course);

            TextView Year = new TextView(getApplication());
            Year.setText("" + c.getInt(5));
            Year.setTextSize(15);
            Year.setGravity(Gravity.CENTER);
            Year.setTextColor(Color.BLACK);
            Year.setPadding(20,30,20,20);
            trow.addView(Year);

            tbl.addView(trow);

        }
        else {
            Toast.makeText(getApplication(), "No Records Found!", Toast.LENGTH_SHORT).show();
        }
    }
    public void refId(){
        id = (EditText) findViewById(R.id.searchIdTextId);
        course = (EditText) findViewById(R.id.searchCourseTextId);
        searchID = (Button) findViewById(R.id.searchIdBtn);
        searchCourse = (Button) findViewById(R.id.searchCourseBtn);
        tbl = (TableLayout) findViewById(R.id.searchTblId);
    }
}
