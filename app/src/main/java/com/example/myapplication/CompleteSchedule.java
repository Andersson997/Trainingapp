package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CompleteSchedule extends AppCompatActivity
{
      private ArrayList<String> listForListView = new ArrayList<String>();
      private ArrayList<Schedule> schedules;
      private Database db;
      private ListView completeScheduleListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completeschedule);

        completeScheduleListView = (ListView) findViewById(R.id.completeschedulelistview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.schedulelayout, R.id.rowTextView,createListForListView());
        completeScheduleListView.setAdapter(arrayAdapter);

        setListener();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {

                switch (item.getItemId())
                {
                    case R.id.mittschema:
                        startActivity(new Intent(getApplicationContext(), MySchedule.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
    private ArrayList createListForListView()
    {
        db = new Database(CompleteSchedule.this);
        schedules = new ArrayList<>();
        for (Schedule e: db.listOfAllSchedule())
        {
            listForListView.add(e.getNamn());
            schedules.add(e);
        }
        return listForListView;
    }
    private void setListener(){
        completeScheduleListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent nextpage = new Intent(CompleteSchedule.this, SelectedSchedule.class);
                        nextpage.putExtra("schemaId", schedules.get(position).getId());
                        startActivity(nextpage);

                    }
                });

    }

}






