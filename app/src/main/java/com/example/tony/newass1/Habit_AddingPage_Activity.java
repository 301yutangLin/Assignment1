package com.example.tony.newass1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Habit_AddingPage_Activity extends AppCompatActivity {

    public Habit_Class new_habit;
    public ArrayList<String> tempList;

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText edit_date, edit_habit;
    private int year, month, day;

    private String habit_title;
    private Date new_habit_date;
    private ArrayList<Habit_Class> habit = new ArrayList<Habit_Class>();
    private static final String FILENAME = "test1.sav";

    private Button save_Button, cancel_Button;
    private CheckBox mon_box, tue_box, wed_box, thu_box, fri_box, sat_box, sun_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit__adding_page_);

        edit_habit = (EditText)findViewById(R.id.edit_habit_Text);
        edit_date = (EditText)findViewById(R.id.date_editText);

        //setup the calendar
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //setup the checkbox
        mon_box = (CheckBox) findViewById(R.id.Mon_checkBox);
        tue_box = (CheckBox)findViewById(R.id.Tue_checkBox);
        wed_box = (CheckBox)findViewById(R.id.Wed_checkBox);
        thu_box = (CheckBox)findViewById(R.id.Thu_checkBox);
        fri_box = (CheckBox)findViewById(R.id.Fri_checkBox);
        sat_box = (CheckBox)findViewById(R.id.Sat_checkBox);
        sun_box = (CheckBox)findViewById(R.id.Sun_checkBox);

        cancel_Button = (Button)findViewById(R.id.adding_canel_button);
        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save_Button = (Button)findViewById(R.id.save_button);
        save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habit_title = edit_habit.getText().toString();//habit = user input text
                new_habit = new Habit_Class(habit_title); //create the user input habit class
                tempList = new ArrayList<String>();

                if(mon_box.isChecked()){
                    tempList.add("Mon");
                }else{
                    tempList.remove("Mon");
                }
                if(tue_box.isChecked()){
                    tempList.add("Tue");
                }else{
                    tempList.remove("Tue");
                }
                if(wed_box.isChecked()){
                    tempList.add("Wed");
                }else{
                    tempList.remove("Wed");
                }
                if(thu_box.isChecked()){
                    tempList.add("Thu");
                }else{
                    tempList.remove("Thu");
                }
                if(fri_box.isChecked()){
                    tempList.add("Fri");
                }else{
                    tempList.remove("Fri");
                }
                if(sat_box.isChecked()){
                    tempList.add("Sat");
                }else{
                    tempList.remove("Sat");
                }
                if(sun_box.isChecked()){
                    tempList.add("Sun");
                }else{
                    tempList.remove("Sun");
                }
                new_habit.setHabit_daylist(tempList);

                // Convert string to Date
                // cited website: http://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                new_habit_date = new Date();
                try {
                    new_habit_date = dateFormat.parse(edit_date.getText().toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                new_habit.setDefault_date(new_habit_date);
                loadFromFile();
                habit.add(new_habit);
                saveInFile();
                finish();
            }
        });
    }

    //the following method: setDate, Dialog, DatePickerDialog, showDate are used for setting the calendar (get the
    //calender to pop out
    //cited website: http://www.tutorialspoint.com/android/android_datepicker_control.htm
    @SuppressWarnings("deprecation")
    public void setDate(View view){
        showDialog(999);
        //Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id==999){
            return new DatePickerDialog(this,myDateListener,year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            showDate(i, i1+1, i2); //ask why if not add 1 the month wont add
        }
    };

    private void showDate(int year, int month, int day){
        edit_date.setText(new StringBuilder().append(day).append("-").append(month)
        .append("-").append(year));
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habit, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>(){}.getType();

            habit = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habit = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
