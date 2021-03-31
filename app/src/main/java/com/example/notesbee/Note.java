package com.example.notesbee;
import android.app.AlertDialog;

import java.util.HashMap;

public class Note {
    public String memo;
    public String title;
    public Alarm alarm;

    public Note() {
        memo = "";
        title = "";
        alarm = new Alarm();
    }

    public Note(String serial) {
        try {
            StringBuilder string = new StringBuilder(serial);
            int lengthTitle = Integer.parseInt(string.substring(0, 5));
            int lengthMemo = Integer.parseInt(string.substring(5 + lengthTitle, 5));
            int lengthAlarm = Integer.parseInt(string.substring(5 + lengthTitle + 5 + lengthMemo, 5));
        } catch (Exception e) {
            memo = "";
            title = "";
            alarm = new Alarm();
        }
    }

    // Outputs string size as a n character string, ie 35 character string with 4 characters would be "0035"
    private String lenAsSize(String str, int characters) {
        String len = (Integer.valueOf(str.length())).toString();
        while (len.length() < characters)
            len = "0" + len;
        return len;
    }

    // Outputs a single string with all relevant data encoded in it, you can create a new note class with said string
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
}
