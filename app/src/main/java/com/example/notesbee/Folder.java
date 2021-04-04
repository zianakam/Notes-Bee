package com.example.notesbee;

public class Folder {
    private Note[] notes;
    private String name;

    public String getName() {
        return name;
    }

    public int getNoteCount() {
        return notes.length;
    }

    public Note getNote(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= notes.length)
            throw new ArrayIndexOutOfBoundsException();
        return notes[index];
    }
}
