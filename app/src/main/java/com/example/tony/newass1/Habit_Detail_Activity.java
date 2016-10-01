package com.example.tony.newass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Habit_Detail_Activity extends AppCompatActivity {

    // receive item, detail.sav has the item that we just click on the all habit listview
    private static final String DETAIL_HABIT = "detail.sav";
    private ArrayList<Habit_Class> receive_item_list = new ArrayList<Habit_Class>();
    private Habit_Class chosen_item;
    private int replace_num;

    //private static final String COMPLETED_HABIT = "completed_habit.sav";
    //all the habit
    private static final String FILENAME = "test1.sav";
    private ArrayList<Habit_Class> all_habit_list = new ArrayList<Habit_Class>();
    private Habit_Class update_habit;
    private int chosen_record;

    private Button complete_Button, delete_Button, cancel_Button;

    private TextView habit_title_view, days_view, count_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit__detail_);

        complete_Button = (Button)findViewById(R.id.complete_button);
        delete_Button = (Button)findViewById(R.id.delete_button);
        cancel_Button = (Button)findViewById(R.id.cancel_button);

        habit_title_view = (TextView) findViewById(R.id.chosen_habit_textView);
        days_view = (TextView) findViewById(R.id.chosen_days_textView);
        count_view = (TextView) findViewById(R.id.chosen_count_textView);

        loadFromFile(); //receive_item_list gets the item clicked
        loadFromFile_All_Habit(); // all_habit_list got all the items

        chosen_item = receive_item_list.get(0); //chosen habit is the onclick item
        Log.d("renew", String.valueOf(chosen_item.getRecord()));
        habit_title_view.setText(chosen_item.getHabit_name());
        days_view.setText(chosen_item.getHabit_daylist().toString());
        count_view.setText(String.valueOf(chosen_item.getRecord()));
        Log.d("RUNING SECOND", "RUN SECOND TIME");
        //look for the same item in the all_habit_list
        for(int a =0; a<all_habit_list.size(); a++){
            if(all_habit_list.get(a).equals(chosen_item)){ //if 2 objects are the same
                update_habit = all_habit_list.get(a); //update_habit is the same item as the onclick item.
                replace_num = a;
            }
        }
        Log.d("replace_num", String.valueOf(replace_num));
        chosen_record = update_habit.getRecord();
        Log.d("checking error", "check error");
        complete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen_record = chosen_record + 1;
                Log.d("CHOSEN RECORD", String.valueOf(chosen_record));
                update_habit.setRecord(chosen_record);
                Log.d("what is record?", String.valueOf(chosen_record));
                all_habit_list.set(replace_num, update_habit);
                Log.d("UPDATE RUNNING", "checking update");
                saveInFile();
                finish();
            }
        });

        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all_habit_list.remove(chosen_item);
                saveInFile();
                finish();
            }
        });

        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        loadFromFile_All_Habit();
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(all_habit_list, out);
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

    //all the habit
    private void loadFromFile_All_Habit() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>(){}.getType();

            all_habit_list = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            all_habit_list = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    //load the item
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(DETAIL_HABIT);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>(){}.getType();

            receive_item_list = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receive_item_list = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
