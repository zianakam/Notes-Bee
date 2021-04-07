package com.example.notesbee.ui.notes;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.notesbee.Note;
import com.example.notesbee.R;

import androidx.annotation.NonNull;

public class NotesAdapter extends ArrayAdapter<Note> {
    public NotesAdapter(@NonNull Context context) {
        super(context, R.layout.notes_cardview);
    }
}
