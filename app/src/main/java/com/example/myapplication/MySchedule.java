package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MySchedule extends AppCompatActivity {
    private ArrayList<String> listForListView;
    private ArrayList<Schedule> schedules;
    private Database db;
    private ListView myScheduleListView;
    private int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myschedule);

        userid = ((CurrentlyLoggedInUser)getApplication()).getUser().getId();
        myScheduleListView = (ListView) findViewById(R.id.mainListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.schedulelayout, R.id.rowTextView,createListForListView());
        myScheduleListView.setAdapter(arrayAdapter);
        setListener();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.mittschema);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.mittschema:
                        return true;
                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
    public ArrayList createListForListView()
    {
        db = new Database(MySchedule.this);
        schedules = new ArrayList<>();
        listForListView = new ArrayList<>();

        for (Schedule e: db.getScheduleForUser(userid))
        {
            listForListView.add(e.getNamn());

        }
        for (Schedule e: db.listOfAllSchedule())
        {
            schedules.add(e);
        }
        return listForListView;
    }
    private void setListener(){
        myScheduleListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent schemaSida = new Intent(MySchedule.this, SelectedSchedule.class);
                        schemaSida.putExtra("schemaId", schedules.get(position).getId());
                        startActivity(schemaSida);

                    }
                });

    }

}