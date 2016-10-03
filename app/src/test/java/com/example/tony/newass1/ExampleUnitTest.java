package com.example.tony.newass1;

import org.junit.Test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testAddHabit_Class() {
        Habit_Class habit = new Habit_Class("hello");
        assertEquals(habit.getHabit_name(), "hello");
        assertEquals(habit.getRecord(), 0);
    }
    @Test
    public void testSetDate(){
        String string_date = "11-05-2016";
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        //convert string to date
        //cited website:http://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
        try {
            date = sd.parse(string_date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Habit_Class hc = new Habit_Class("HELLO");
        hc.setDefault_date(date);

        assertEquals(hc.getDefault_date(), date);
    }

    @Test
    public void testtRecord(){
        int i = 3;
        Habit_Class hc = new Habit_Class("hello");
        hc.setRecord(i);
        assertEquals(hc.getRecord(), i);
    }

    @Test
    public void testHabitDayList(){
        ArrayList<String> days_of_week = new ArrayList<String>();
        days_of_week.add("mon");
        days_of_week.add("tue");
        Habit_Class hc = new Habit_Class("hi");
        hc.setHabit_daylist(days_of_week);
        assertEquals(hc.getHabit_daylist(), days_of_week);

    }

    @Test
    public void testEquals(){
        Habit_Class hc1 = new Habit_Class("hello");
        Habit_Class hc2 = new Habit_Class("hello");
        assertTrue(hc1.equals(hc2));
    }

}