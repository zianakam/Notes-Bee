package com.example.notesbee;
import android.app.AlarmManager;
import android.util.Base64;

import java.nio.ByteBuffer;

public class Alarm {
    // Time this alarm is for
    private int year, month, date, hour, minute;
    private boolean timeSet;

    /**
     * Initializes an empty alarm
     */
    public Alarm() {
        year = month = date = hour = minute = 0;
        timeSet = false;
    }

    /**
     * Initializes an alarm from a serialized string returned from Alarm.toString
     */
    public Alarm(String serial) {
        if (serial.equals("")) {
            year = month = date = hour = minute = 0;
            timeSet = false;
        } else {
            ByteBuffer buf = ByteBuffer.wrap(Base64.decode(serial, 0));
            year = buf.getInt();
            month = buf.getInt();
            date = buf.getInt();
            hour = buf.getInt();
            minute = buf.getInt();
            timeSet = buf.getInt() != 0;
        }
    }

    /**
     * Sets the a time for the alarm to go off
     * @param year year the alarm should go off
     * @param month month the alarm should go off
     * @param date date the alarm should go off
     * @param hour hour the alarm should go off
     * @param minute minute the alarm should go off
     */
    public void set(int year, int month, int date, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        timeSet = true;
    }

    /**
     * If no time has been bound to this alarm getTimeSet will return false, otherwise true.
     */
    public boolean getTimeSet() {
        return timeSet;
    }

    /**
     * Returns the alarm's scheduled year or -1 if getTimeSet() returns false
     */
    public int getYear() {
        if (timeSet)
            return this.year;
        else
            return -1;
    }

    /**
     * Returns the alarm's scheduled month or -1 if getTimeSet() returns false
     */
    public int getMonth() {
        if (timeSet)
            return this.month;
        else
            return -1;
    }

    /**
     * Returns the alarm's scheduled date or -1 if getTimeSet() returns false
     */
    public int getDate() {
        if (timeSet)
            return this.date;
        else
            return -1;
    }

    /**
     * Returns the alarm's scheduled hour or -1 if getTimeSet() returns false
     */
    public int getHour() {
        if (timeSet)
            return this.hour;
        else
            return -1;
    }

    /**
     * Returns the alarm's scheduled minute or -1 if getTimeSet() returns false
     */
    public int getMinute() {
        if (timeSet)
            return this.minute;
        else
            return -1;
    }


    public String serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(6 * 4); // 6 variables at 4 bytes a piece
        buffer.putInt(year);
        buffer.putInt(month);
        buffer.putInt(date);
        buffer.putInt(hour);
        buffer.putInt(minute);
        buffer.putInt(timeSet ? 1 : 0);
        return Base64.encodeToString(buffer.array(), Base64.NO_WRAP);
    }

    public String toString() {
        return serialize();
    }
}
