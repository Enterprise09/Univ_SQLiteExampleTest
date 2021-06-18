package com.example.sqliteexampletest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create 'mydb' table use autoincrement keyword
        db.execSQL(
                "CREATE TABLE mydb (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "number number, " +
                        "name char, " +
                        "department char, " +
                        "age number, " +
                        "grade number);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
