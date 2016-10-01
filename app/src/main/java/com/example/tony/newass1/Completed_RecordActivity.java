package com.example.tony.newass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.ArrayList;

public class Completed_RecordActivity extends AppCompatActivity {

    private static final String COMPLETED_FILENAME = "test1.sav";
    private ListView completed_listview;
    private ArrayAdapter<Habit_Class> completed_adapter;
    private ArrayList<Habit_Class> completed_habitList = new ArrayList<Habit_Class>();
    private Habit_Class completed_habit;

    private static final String DETAIL_HABIT = "detail.sav";
    private ArrayList<Habit_Class> completedHabitList = new ArrayList<Habit_Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed__record);


        completed_listview = (ListView) findViewById(R.id.Completed_listView);


        Button viewButton = (Button) findViewById(R.id.view_habit_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Completed_RecordActivity.this, Habit_Editor_Activity.class);
                startActivity(intent);
            }
        });

        completed_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //https://developer.android.com/reference/android/widget/AdapterView.html#getItemAtPosition%28int%29
                completed_habit = (Habit_Class) adapterView.getItemAtPosition(i);
                completedHabitList.clear();
                completedHabitList.add(completed_habit);
                saveInFile();
                Intent detail_intent = new Intent(Completed_RecordActivity.this, Deleted_RecordActivity.class);
                startActivity(detail_intent);

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        for(int i=0; i<completed_habitList.size(); i++) {
            if (completed_habitList.get(i).getRecord() == 0) {
                completed_habitList.remove(i);
            }
        }
        completed_adapter = new ArrayAdapter<Habit_Class>(this,R.layout.list_item, completed_habitList);
        completed_listview.setAdapter(completed_adapter);
    }
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(COMPLETED_FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>(){}.getType();

            completed_habitList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            completed_habitList = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(DETAIL_HABIT, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(completed_habitList, out);
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