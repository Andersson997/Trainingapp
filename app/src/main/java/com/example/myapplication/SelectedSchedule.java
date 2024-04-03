package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SelectedSchedule extends AppCompatActivity {
    private ArrayList<String> listforListview;
    private ArrayList<Excercise> excercises;
    private ArrayList<Schedule> schedules;
    private Database db;
    private ListView selectedScheduleListView;
    private Button add;
    private int id;
    private int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedschedule);
            userid = ((CurrentlyLoggedInUser)getApplication()).getUser().getId();
            id = getIntent().getIntExtra("schemaId", 0);
            selectedScheduleListView = (ListView) findViewById(R.id.selectedschedulelistview);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.schedulelayout, R.id.rowTextView,createlist());
            selectedScheduleListView.setAdapter(arrayAdapter);
            setListener();
            setClicklistener();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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
    private ArrayList createlist()
    {
        db = new Database(SelectedSchedule.this);
        listforListview = new ArrayList<String>();
        excercises = new ArrayList<Excercise>();
        for (Excercise e: db.getExcercisesForSchedule(id))
        {
            listforListview.add(e.getName());
        }
        for (Excercise e: db.listOfAllExcercise())
        {
            excercises.add(e);
        }
        return listforListview;
    }
    private void setListener()
    {
        selectedScheduleListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Intent nextpage = new Intent(SelectedSchedule.this, ExcerciseDescription.class);
                        nextpage.putExtra("excerciseid", excercises.get(position).getId());
                        startActivity(nextpage);
                    }
                });
    }
    private void setClicklistener()
    {
        db = new Database(SelectedSchedule.this);
        add = findViewById(R.id.add);
        schedules = new ArrayList<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Schedule e: db.listOfAllSchedule())
                {
                    if (e.getId()==id)
                    {
                        schedules.add(e);
                        Toast.makeText(SelectedSchedule.this, "Tillagd", Toast.LENGTH_SHORT).show();
                    }

                }
                db.addScheduleForUser(schedules,userid);
            }
        });
    }
}