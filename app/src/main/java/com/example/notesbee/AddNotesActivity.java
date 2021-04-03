package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private String dateTime;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

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

    /**
     * Function called when the time is chosen from the alarm.
     */
    public void onTimeChanged(TimePicker view, int hour, int minute) {
        System.out.println(hour + ":" + minute);
    }

    /**
     * The function that is called when the "Alarm" button is pressed.
     * It will launch a dialogue to select a time and schedule an alarm for that time.
     */
    public void setAlarm(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), this::onTimeChanged, 0, 0, false);
        timePickerDialog.show();
    }

    /**
     * Adds the message currently written to the database, called whenever something would
     * cause this message screen to close.
     */
    private void addDataToDatabase(){
        // We will save all data in a note class then serialize it
        Note note = new Note();
        note.title = title.getText().toString();
        note.memo = content.getText().toString();
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        // save all three variables to the database


    }
}