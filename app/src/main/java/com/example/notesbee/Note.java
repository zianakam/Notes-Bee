package com.example.notesbee;
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
        // TODO: Deserialize
    }

    // Outputs string size as a n character string, ie 35 character string with 4 characters would be "0035"
    private String lenAsSize(String str, int characters) {
        String len = (Integer.valueOf(str.length())).toString();
        while (len.length() < characters)
            len = "0" + len;
        return len;
    }

    public String serialize() {
        // Serial format is as follows:
        //  + 5 characters for title size
        //  + title
        //  + 5 characters for memo size
        //  + memo
        //  + remainder is alarm serialized string
        String serial = "";

        return ""; // TODO: This
    }
}
