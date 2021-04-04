package com.example.notesbee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    //TABLE NAME
    public static final String TABLE_NAME = "NotesBee";

    //TABLE COLUMNS
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESC = "description";
    public static final String DATE = "date";
    //DATABASE INFORMATION
    public static final String DATABASE_NAME = "NOTES_BEE.DB";

    //DATABASE VERSION
    static final int DB_VERSION = 1;

    //CREATING TABLE QUERY
    private static final String CREATE_TABLE
            = "CREATE TABLE "
            + TABLE_NAME +
            " ("
            + ID +
            " INTEGER PRIMARY KEY, "
            + TITLE +
            " TEXT NOT NULL, "
            + DESC +
            " TEXT, "
            + DATE +
            " TEXT);"
            ;

    //CONSTRUCTOR
    public Database (Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //EXECUTING THE QUERY
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exists " + TABLE_NAME);
        onCreate(db);
    }
    //
}
