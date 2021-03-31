package com.example.notesbee;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private Database dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //Constructor
    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new Database(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.SUBJECT, name);
        contentValues.put(Database.DESC, desc);
        contentValues.put(Database.SUBJECT, name);
    }

}
