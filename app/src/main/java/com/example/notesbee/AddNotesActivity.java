package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.sql.Time;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private Date date;
    private Time time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title=findViewById(R.id.notesTitle);
        content=findViewById(R.id.notesContent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        addDataToDatabase();
    }

    @Override
    protected void onStop() {
        super.onStop();
        addDataToDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addDataToDatabase();
    }

    private void addDataToDatabase(){
        // We will save all data in a note class then serialize it
        Note note = new Note();
        note.title = title.getText().toString();
        note.memo = content.getText().toString();
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created

        // save all four varibles tpo the database

    }
}