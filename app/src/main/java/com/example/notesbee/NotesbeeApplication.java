package com.example.notesbee;

import android.app.Application;

import com.example.notesbee.ui.NoteList;

/**
 * This class serves as effectively a global namespace for all other pieces
 * of the application to pull from.
 */
public class NotesbeeApplication extends Application {
    // Global variables
    private NoteList database;

    /**
     * Sets the internal database
     * @param db New database to use
     */
    public void setDatabase(NoteList db) {
        database = db;
    }

    /**
     * Returns the database, may be null
     */
    public NoteList getDatabase() {
        return database;
    }
}
