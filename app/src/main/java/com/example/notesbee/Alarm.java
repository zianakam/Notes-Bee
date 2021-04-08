package com.example.notesbee;
import android.app.AlarmManager;

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
        year = month = date = hour = minute = 0;
        timeSet = false;
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
        return ""; // TODO: This
    }

    public String toString() {
        return serialize();
    }
}
