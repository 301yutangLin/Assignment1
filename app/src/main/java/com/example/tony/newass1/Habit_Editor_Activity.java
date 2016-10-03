package com.example.tony.newass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Habit_Editor_Activity extends AppCompatActivity {

    //copy note from the LonelyTwitterActivity.java (author: Shida He)
    private static final String FILENAME = "test1.sav";

    private ListView habitListView;
    private ArrayAdapter<Habit_Class> adapter;
    private ArrayList<Habit_Class> habitList = new ArrayList<Habit_Class>();
    private Button add_button, backto_button;

    //other file to save the item onclick of habitListView
    private static final String DETAIL_HABIT = "detail.sav";
    private ArrayList<Habit_Class> detailHabitList = new ArrayList<Habit_Class>();
    private Habit_Class detail_habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit__editor_);

        habitListView = (ListView)findViewById(R.id.habit_listView);

        //goes to the Habit_AddingPage_Activity
        add_button = (Button)findViewById(R.id.add_habit_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Habit_Editor_Activity.this, Habit_AddingPage_Activity.class);
                startActivity(intent2);
            }
        });

        backto_button = (Button)findViewById(R.id.back_button);
        backto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //https://developer.android.com/reference/android/widget/AdapterView.html#getItemAtPosition%28int%29
                detail_habit  = (Habit_Class)adapterView.getItemAtPosition(i);
                detailHabitList.clear();
                detailHabitList.add(detail_habit);
                saveInFile();
                Intent detail_intent = new Intent(Habit_Editor_Activity.this, Habit_Detail_Activity.class);
                startActivity(detail_intent);
            }
        });
    }

    @Override
    //https://github.com/shidahe/lonelyTwitter
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //adapter.notifyDataSetChanged();
        loadFromFile();
        adapter = new ArrayAdapter<Habit_Class>(this,R.layout.list_item, habitList);
        habitListView.setAdapter(adapter);
    }
    //https://github.com/shidahe/lonelyTwitter
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME); //test1.sav
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>(){}.getType();

            habitList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    //https://github.com/shidahe/lonelyTwitter
    //save the file for the onclick item
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(DETAIL_HABIT,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(detailHabitList, out);
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


}
