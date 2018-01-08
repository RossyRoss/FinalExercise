package com.example.pc_user.finalexercise;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class ViewActivity extends AppCompatActivity {
    TableLayout tbl;
    SQLiteDatabase dbase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        tbl = (TableLayout) findViewById(R.id.tblId);
        DBHelper helper = new DBHelper(getApplication(),"schoolDB",1);
        dbase = helper.getReadableDatabase();
        display();
    }

    public void display(){
        Cursor c;
        String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};

        c = dbase.query("StudentFile", fields, null,null,null,null,null,null);


        c.moveToFirst();

        if(!c.isAfterLast()){
            Toast.makeText(getApplication(),"Records found!", Toast.LENGTH_SHORT).show();

            do{
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

            }while(c.moveToNext());
        }
        else{
            Toast.makeText(getApplication(), "No Records Found!", Toast.LENGTH_SHORT).show();

        }
    }
}
