package com.example.pc_user.finalexercise;

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

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase dbase;
    Button reg, login;
    EditText userName, password;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        refId();
        DBHelper helper = new DBHelper(getApplication(), "schoolDB", 1);
        dbase = helper.getWritableDatabase();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = userName.getText().toString();
                pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(getApplication(), "Please input missing fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor c;
                    String[] fields = {"UserName", "FirstName", "LastName", "Password"};

                    c = dbase.query("UserFile", fields, "UserName = '" + user + "' AND Password = '" + pass + "'", null, null, null, null, null);
                    c.moveToFirst();

                    if (!c.isAfterLast()) {
                        Toast.makeText(getApplication(), "Welcome " + user + "!", Toast.LENGTH_SHORT).show();
                        Intent studentIntent = new Intent(view.getContext(), StudentActivity.class);
                        startActivity(studentIntent);
                    } else {
                        Toast.makeText(getApplication(), "Invalid username or password.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }

    public void refId(){
        userName = (EditText) findViewById(R.id.userLoginId);
        password = (EditText) findViewById(R.id.userPassId);
        reg = (Button) findViewById(R.id.registerBtn);
        login = (Button) findViewById(R.id.loginBtn);


    }
}
