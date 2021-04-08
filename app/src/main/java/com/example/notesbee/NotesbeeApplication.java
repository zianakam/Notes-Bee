package com.example.notesbee;

import android.app.Application;

import com.example.notesbee.ui.NoteList;

/**
 * This class serves as effectively a global namespace for all other pieces
 * of the application to pull from.
 */
public class NotesbeeApplication extends Application {
    //////////// Global variables ////////////
    // Database for the whole app
    private NoteList database = null;

    // Currently selected note and whether or not it has meaning
    private int selectedNoteIndex = 0;
    private boolean noteSelected = false;

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

    /**
     * For communicating between the note frag and add note activity - tells the latter which note to work on
     * @param note Index of a note in the database
     */
    public void setSelectedNoteIndex(int note) {
        selectedNoteIndex = note;
        noteSelected = true;
    }

    /**
     * Grabs the index set through setSelectedNoteIndex. If nothing was bound, NoteList.NOTELIST_NEW_NOTE is returned
     * and after this function call the selected note will be unbound.
     */
    public int getSelectedNoteIndex() {
        boolean ns = noteSelected;
        noteSelected = false;
        if (ns)
            return selectedNoteIndex;
        else
            return NoteList.NOTELIST_NEW_NOTE;
    }
}
