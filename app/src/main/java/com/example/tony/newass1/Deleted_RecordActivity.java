package com.example.tony.newass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Deleted_RecordActivity extends AppCompatActivity {

    private static final String DETAIL_HABIT = "detail.sav"; //file that save the item click for deletion
    private static final String COMPLETED_HABIT = "completed_habit.sav"; //file that has the habit history
    private Button delete_h, delete_c;
    private TextView title_view, day_view, time_view, record_view;
    private Habit_Class history_habitItem, get_deletion_habit;
    private ArrayList<Habit_Class> delete_historyListItem = new ArrayList<Habit_Class>();
    private ArrayList<Habit_Class> delete_historyList = new ArrayList<Habit_Class>();

    //detail.sav has the onclick item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted__record);
        //set up all the button and textview
        delete_h = (Button)findViewById(R.id.record_delete);
        delete_c = (Button)findViewById(R.id.record_cancel);
        title_view = (TextView)findViewById(R.id.TITLE_textView);
        day_view = (TextView)findViewById(R.id.DAY_textView);
        time_view = (TextView)findViewById(R.id.TIME_textView);
        record_view = (TextView)findViewById(R.id.RECORD_textView);

        //get the habit completion list and the object wanted to delete
        loadFromFileItem();//detail.sav -> delete_historyListItem
        loadFromFile();//completed_habit.sav -> delete_historyList

        //get the onclick item
        history_habitItem = delete_historyListItem.get(0);

        //look for the item in the arraylist
        for(int z =0; z<delete_historyList.size(); z++){
            if(delete_historyList.get(z).equals(history_habitItem)){
                get_deletion_habit = delete_historyList.get(z);
            }
        }
        //set up the textview
        title_view.setText(history_habitItem.getHabit_name());
        day_view.setText(history_habitItem.getHabit_daylist().toString());
        record_view.setText(String.valueOf(history_habitItem.getRecord()));
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        time_view.setText(df.format(history_habitItem.getDefault_date()));

        //button to cancel and close the activity
        delete_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //delete the onclick item from the list
        delete_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_historyList.remove(get_deletion_habit);
                saveInFile();
                finish();
            }
        });

    }

    ////https://github.com/shidahe/lonelyTwitter
    private void loadFromFileItem() {
        try {
            FileInputStream fis = openFileInput(DETAIL_HABIT);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>() {
            }.getType();
            //completed_habitList has the habit that has been done
            delete_historyListItem = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            delete_historyListItem = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }//detail.sav -> delete_historyListItem

    //https://github.com/shidahe/lonelyTwitter
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(COMPLETED_HABIT);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit_Class>>() {
            }.getType();
            //completed_habitList has the habit that has been done
            delete_historyList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            delete_historyList = new ArrayList<Habit_Class>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }//completed_habit.sav -> delete_historyList

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(COMPLETED_HABIT,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(delete_historyList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }// completed_habit.save <-delete_historyList
}