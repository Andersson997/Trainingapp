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

public class ExcerciseOption extends AppCompatActivity {
    private ArrayList<String> listForListView;
    private ArrayList<Excercise> excercises;
    private Database db;
    private ListView excerciseOptionListView;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excerciseoption);
        excerciseOptionListView = (ListView) findViewById(R.id.excerciseoptionlistview);
        db = new Database(ExcerciseOption.this);
        myAdapter = new ArrayAdapter<String>(ExcerciseOption.this, R.layout.schedulelayout, R.id.rowTextView,createlist());
        excerciseOptionListView.setAdapter(myAdapter);
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
    private ArrayList<String> createlist()
    {
        db = new Database(ExcerciseOption.this);
        listForListView = new ArrayList<>();
        excercises = new ArrayList<>();
        for (Excercise e: db.listOfAllExcercise())
        {
            listForListView.add(e.getName());
        }
        for (Excercise e: db.listOfAllExcercise())
        {
            excercises.add(e);
        }
        return listForListView;
    }
    private void setListener()
    {
        excerciseOptionListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Intent nextpage = new Intent(ExcerciseOption.this, ExcerciseDescription.class);
                        nextpage.putExtra("excerciseid", excercises.get(position).getId());
                        startActivity(nextpage);
                    }
                });
    }
    
}