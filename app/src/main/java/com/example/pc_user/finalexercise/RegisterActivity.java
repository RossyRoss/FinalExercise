package com.example.pc_user.finalexercise;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    SQLiteDatabase dbase;
    Button reg, clear;
    EditText userName, firstName, lastName, password, confPass;
    String user,fname,lname,pass,confpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        refId();
        DBHelper helper = new DBHelper(getApplication(), "schoolDB", 1);
        dbase = helper.getWritableDatabase();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = userName.getText().toString();
                fname = firstName.getText().toString();
                lname = lastName.getText().toString();
                pass = password.getText().toString();
                confpass = confPass.getText().toString();


                if(user.equals("") || fname.equals("") || lname.equals("") || pass.equals("") || confpass.equals("")) {
                    Toast.makeText(getApplication(), "Please input missing fields.", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(confpass))
                    Toast.makeText(getApplication(), "Password doesn't match.", Toast.LENGTH_SHORT).show();
                else {
                    Cursor c;
                    String[] fields = {"UserName", "FirstName", "LastName", "Password"};

                    c = dbase.query("UserFile", fields, "UserName = '" + user +"'", null, null, null, null, null);
                    c.moveToFirst();
                    if (!c.isAfterLast()) {
                        Toast.makeText(getApplication(), "Account already exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        addUser(user, fname, lname, pass);
                        Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
                        startActivity(loginIntent);
                    }

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText("");
                firstName.setText("");
                lastName.setText("");
                password.setText("");
                confPass.setText("");
            }
        });
    }

    public void addUser(String user, String fname, String lname, String pass){
        try {
            ContentValues data = new ContentValues();

            data.put("UserName", user);
            data.put("FirstName", fname);
            data.put("LastName", lname);
            data.put("Password", pass);

            dbase.insert("UserFile", null, data);

            Toast.makeText(getApplication(), "SUCCESSFULLY ADDED", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void refId(){
        userName = (EditText) findViewById(R.id.userRegId);
        firstName = (EditText) findViewById(R.id.firstNameRegId);
        lastName = (EditText) findViewById(R.id.lastNameRegId);
        password = (EditText) findViewById(R.id.passRegId);
        confPass = (EditText) findViewById(R.id.confPassRegId);
        reg = (Button) findViewById(R.id.regBtn);
        clear = (Button) findViewById(R.id.clearBtn);


    }
}
