package com.example.pc_user.finalexercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc-user on 17/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context c, String db, int dbVer){
        super(c, db, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Usersql = "CREATE TABLE UserFile" +
                "(Id integer primary key, UserName text not null, FirstName text, " +
                "LastName text, Password text)";

        String Studentsql = "CREATE TABLE StudentFile" +
                "(IdNumber text primary key not null, LastName text, " +
                "FirstName text, MiddleInitial text, Course text, Year integer)";

        db.execSQL(Usersql);
        db.execSQL(Studentsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String user = "DROP TABLE IF EXISTS UserFile";
        String student = "DROP TABLE IF EXISTS StudentFile";

        db.execSQL(user);
        db.execSQL(student);
        onCreate(db);
    }

}