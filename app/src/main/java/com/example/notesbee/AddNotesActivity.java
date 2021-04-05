package com.example.notesbee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

    // For choosing date and time of an alarm
    private int year;
    private int month;
    private int day;

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
        System.out.println("Alarm set for " + year + "-" + month + "-" + day + " @ " + hour + ":" + minute);
        // TODO: Make an alarm for the chosen time
    }

    /**
     * This is called when the user chooses a date for the alarm, to which it will save the date
     * and present the time picker
     */
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), this::onTimeChanged, 0, 0, false);
        timePickerDialog.show();
    }

    /**
     * The function that is called when the "Alarm" button is pressed.
     * It will launch a dialogue to select a time and schedule an alarm for that time.
     */
    public void setAlarm(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), this::onDateSet, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
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
        Note note = new Note();
        note.title = title.getText().toString();
        note.memo = content.getText().toString();
        String serial = note.serialize();

        // TODO: Serialize and add to database
        // get time and date when the note is created

        // save all four variables to the database

    }
}