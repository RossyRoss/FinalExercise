package com.example.pc_user.finalexercise;


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

public class DeleteActivity extends AppCompatActivity {
    Button deleteBtn;
    EditText id;
    String IdText;
    SQLiteDatabase dbase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);
        DBHelper helper = new DBHelper(getApplication(), "schoolDB", 1);
        dbase = helper.getWritableDatabase();
        refId();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdText = id.getText().toString();
                Cursor c;
                String[] fields = {"IdNumber", "LastName", "FirstName", "MiddleInitial", "Course", "Year"};
                c = dbase.query("StudentFile", fields, "IdNumber = '" + IdText + "'", null, null, null, null, null);
                c.moveToFirst();

                if (IdText.equals("")) {
                    Toast.makeText(getApplication(), "Please input id field.", Toast.LENGTH_SHORT).show();
                } else if(c.isAfterLast()) {
                    Toast.makeText(getApplication(), "Id Number does not exist.", Toast.LENGTH_SHORT).show();
                } else {
                    dbase.delete("StudentFile", "IdNumber = '" + IdText + "'", null);
                    Toast.makeText(getApplication(), "Successfully deleted.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void refId(){
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        id = (EditText) findViewById(R.id.idTextDeleteId);
    }
}