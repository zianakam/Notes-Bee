package com.example.notesbee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //DB.execSQL("CREATE TABLE Notes(Title TEXT primary key, Content TEXT, Date TEXT, Alarm Boolean)");
        DB.execSQL("create Table Userdetails(title TEXT primary key, description TEXT, date TEXT, alarm Boolean)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insert_user_data(String title, String description, String date, String alarm)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Description", description);
        contentValues.put("Date", date);
        contentValues.put("Alarm", alarm);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean update_user_data(String title, String description, String date, String alarm) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Description", description);
        contentValues.put("Date", date);
        contentValues.put("Alarm", alarm);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.update("User details", contentValues, "name=?", new String[]{title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean delete_data (String title)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.delete("User details", "name=?", new String[]{title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor get_data ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;

    }
}



