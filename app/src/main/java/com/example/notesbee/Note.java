package com.example.notesbee;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Date;
import java.text.DateFormat;

import java.util.Calendar;
import java.util.HashMap;

public class Note {
    public String memo;
    public String title;
    public Alarm alarm;

    // For choosing date and time of an alarm
    private int year;
    private int month;
    private int day;

    /**
     * Initializes a note with nothing in it.
     */
    public Note() {
        memo = "";
        title = "";
        alarm = new Alarm();
    }

    /**
     * Creates a note from a serialized note string
     * @param serial String returned by Note::serialize
     */
    public Note(String serial) {
        try {
            StringBuilder string = new StringBuilder(serial);
            int lengthTitle = Integer.parseInt(string.substring(0, 5));
            int lengthMemo = Integer.parseInt(string.substring(5 + lengthTitle, 5 + (5 + lengthTitle)));
            int lengthAlarm = Integer.parseInt(string.substring(5 + lengthTitle + 5 + lengthMemo, 5 + (5 + lengthTitle + 5 + lengthMemo)));
            title = string.substring(5, 5 + lengthTitle);
            memo = string.substring(5 + lengthTitle + 5, lengthMemo + (5 + lengthTitle + 5));
            alarm = new Alarm(string.substring(5 + lengthTitle + 5 + lengthMemo + 5));
        } catch (Exception e) {
            memo = "";
            title = "";
            alarm = new Alarm();
        }
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
     * Creates a dialogue for setting an alarm, starting with a calendar then a clock dialog
     * @param context Context to make the calendar and clock dialog in
     */
    public void createAlarm(Context context) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, this::onDateSet, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * Outputs string size as a n character string, ie 35 character string with 4 characters would be "0035"
     * @param str String to take the length of
     * @param characters Minimum number of characters for the resultant string to be
     * @return Returns the string padded with 0s
     */
    private String lenAsSize(String str, int characters) {
        String len = (Integer.valueOf(str.length())).toString();
        while (len.length() < characters)
            len = "0" + len;
        return len;
    }

    /**
     * Outputs a single string with all relevant data encoded in it, you can create a new note class with said string
     * @return Returns the note class serialized into a string
     */
    public String serialize() {
        // Serial format is as follows:
        //  + 5 characters for title size
        //  + title
        //  + 5 characters for memo size
        //  + memo
        //  + 5 characters for serialized alarm size
        //  + alarm serialized string
        return lenAsSize(title, 5) +
               title +
               lenAsSize(memo, 5) +
               memo +
               lenAsSize(alarm.serialize(), 5) +
               alarm.serialize();
    }

    /**
     * Outputs a single string with all relevant data encoded in it, you can create a new note class with said string
     * @return Returns the note class serialized into a string
     */
    public String toString() {
        return serialize();
    }
}
