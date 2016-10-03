package com.example.tony.newass1;


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


    //constructor of the Habit_Class which takes in a string parameter and
    //set up the title, time and record
    public Habit_Class(String h_name) {
        this.habit_name = h_name;
        this.default_date = new Date();
        this.record = 0;
    }
    //allow to set date
    public void setDefault_date(Date default_date) {
        this.default_date = default_date;
    }
    //return the class's date
    public Date getDefault_date() {
        return default_date;
    }
    //return the habit name
    public String getHabit_name() {
        return habit_name;
    }
    //return the habit's completion time
    public int getRecord() {
        return record;

    }
    //take in int parameter and set it as the habit's completion time
    public void setRecord(int record) {
        this.record = record;
    }
    //return the Arraylist of String which has the days of the week
    public ArrayList<String> getHabit_daylist() {
        return habit_daylist;
    }
    //take in ArrayList parameter and set it as habit's days of the week
    public void setHabit_daylist(ArrayList<String> habit_daylist) {
        this.habit_daylist = habit_daylist;
    }
    // return a string that has habit's name, habit's date, and habit's days of the week
    @Override
    public String toString(){
        return  habit_name + "\n" + default_date.toString() + " \n " + habit_daylist
                +"\n" + "Record: " + String.valueOf(record);
    }
    //use to compare whether two Habit_Class objects are the same or not
    public boolean equals(Habit_Class hc) {
        //if the entered paramenter is not Habit_Class, return false
        if (!(hc instanceof Habit_Class)) {
            return false;
        }//else true if habit names, daylists and records are the same
        return this.getHabit_name().equals(hc.getHabit_name())
                && this.getDefault_date().equals(hc.getDefault_date())
                && this.getHabit_daylist().equals(hc.getHabit_daylist())
                && this.getRecord() == hc.getRecord();
    }

}

