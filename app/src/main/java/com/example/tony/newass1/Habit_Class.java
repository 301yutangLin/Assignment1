package com.example.tony.newass1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tony on 25/09/16.
 */
public class Habit_Class {
    private String habit_name;
    private Date default_date;
    private ArrayList<String> habit_daylist = new ArrayList<>();
    private int record ;



    public Habit_Class(String h_name) {
        this.habit_name = h_name;
        this.default_date = new Date();
        this.record = 0;
    }

    public void setDefault_date(Date default_date) {
        this.default_date = default_date;
    }

    public Date getDefault_date() {
        return default_date;
    }

    public String getHabit_name() {
        return habit_name;
    }

    public int getRecord() {
        Log.d("GET RECORD", String.valueOf(this.record));
        return record;

    }

    public void setRecord(int record) {
        this.record = record;
        Log.d("SET RECORD", "efgefgefgefgefgefg");
    }

    public ArrayList<String> getHabit_daylist() {
        return habit_daylist;
    }
    public void setHabit_daylist(ArrayList<String> habit_daylist) {
        this.habit_daylist = habit_daylist;
    }

    @Override
    public String toString(){
        return  habit_name + "\n" + default_date.toString() + " \n " + habit_daylist
                +"\n" + "Record: " + String.valueOf(record);
    }

    public boolean equals(Habit_Class hc) {
        if (!(hc instanceof Habit_Class)) {
            return false;
        }

        return this.getHabit_name().equals(hc.getHabit_name())
                && this.getDefault_date().equals(hc.getDefault_date())
                && this.getHabit_daylist().equals(hc.getHabit_daylist())
                && this.getRecord() == hc.getRecord();
    }

    public void printbug(){
        Log.d("abc", this.toString() );
    }
}

