package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.richeditor.RichEditor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private RichEditor notesContent;
    private Boolean alarm;
    private LocalDateTime dateTime;
    //private String dateTime;
    //private Calendar calendar;
    //private SimpleDateFormat simpleDateFormat;
    private Note note; // Local note used to create alarms and build the save/load with



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title=findViewById(R.id.notesTitle);
        ImageButton savenotes= findViewById(R.id.save_note_btn);
        savenotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToDatabase();
            }
        });
        note = new Note();

        // Save data to the database on clicking the save button
        findViewById(R.id.save_note_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToDatabase();
            }
        });


        notesContent= findViewById(R.id.notesContent);
        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.setItalic();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesContent.setUnderline();
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private Boolean isChanged=true;
            @Override
            public void onClick(View view) {
                notesContent.setTextColor(isChanged ? Color.BLACK: Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesContent.setBullets();
            }
        });

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesContent.setNumbers();
            }
        });

        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesContent.insertTodo();
            }
        });





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
        // Must update note contents before setting alarm so the contents are visible to the future notification
        note.title = title.getText().toString();
        //note.memo = content.getText().toString();
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
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created

        // save all four variables to the database

    }
}