package com.example.notesbee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    //private Database dbHelper;
    private Context context;
    private SQLiteDatabase database;
    /**
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
        database.insert(Database.TABLE_NAME, null, contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[] {Database.ID,
        Database.SUBJECT, Database.DESC};

        Cursor cursor = database.query(Database.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.SUBJECT, name);
        contentValues.put(Database.DESC, desc);

        int i = database.update(Database.TABLE_NAME,
                contentValues, Database.ID +
                " = " + id, null);
        return i;
    }
    public void delete(long id){
        database.delete(Database.TABLE_NAME,Database.ID +
                        " = " + id, null);
    }**/
}
