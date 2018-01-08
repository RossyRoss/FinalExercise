package com.example.pc_user.finalexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class StudentActivity extends AppCompatActivity {
    Button add, search, update, view, delete, logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity);
        refId();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(view.getContext(), AddActivity.class);
                startActivity(addIntent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(view.getContext(), UpdateActivity.class);
                startActivity(updateIntent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(view.getContext(), ViewActivity.class);
                startActivity(viewIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteIntent = new Intent(view.getContext(), DeleteActivity.class);
                startActivity(deleteIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
                Toast.makeText(getApplication(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                startActivity(loginIntent);
            }
        });
    }

    public void refId(){
        add = (Button) findViewById(R.id.addBtn);
        search = (Button) findViewById(R.id.searchBtn);
        update = (Button) findViewById(R.id.updateBtn);
        view = (Button) findViewById(R.id.viewBtn);
        delete = (Button) findViewById(R.id.deleteBtn);
        logout = (Button) findViewById(R.id.exitBtn);
    }
}
