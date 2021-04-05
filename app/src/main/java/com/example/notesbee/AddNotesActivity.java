package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private String dateTime;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private Note note; // Local note used to create alarms and build the save/load with

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title=findViewById(R.id.notesTitle);
        content=findViewById(R.id.notesContent);
        ImageButton savenotes= findViewById(R.id.save_note_btn);
        savenotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToDatabase();
            }
        });
        note = new Note();
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

    /**
     * The function that is called when the "Alarm" button is pressed.
     * It will launch a dialogue to select a time and schedule an alarm for that time.
     */
    public void setAlarm(View view) {
        note.createAlarm(view.getContext());
    }

    /**
     * Begins voice recognition, this is called when the microphone icon is pressed.
     */
    public void startVoiceToText(View view) {

    }

    /**
     * Adds the message currently written to the database, called whenever something would
     * cause this message screen to close.
     */
    private void addDataToDatabase(){
        // We will save all data in a note class then serialize it
        note.title = title.getText().toString();
        note.memo = content.getText().toString();
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created

        // save all four variables to the database

    }
}