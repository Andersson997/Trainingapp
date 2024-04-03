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

public class ExcerciseDescription extends AppCompatActivity {
    int id;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_description);
        id = getIntent().getIntExtra("excerciseid",0);
        description = findViewById(R.id.description);
        description.setText(setDescription());
        description.setShadowLayer(10f,1.5f,1.3f, Color.BLACK);
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
    private String setDescription()
    {
        Database db = new Database(ExcerciseDescription.this);
        String description = "";
        for (Excercise e: db.listOfAllExcercise())
        {
         if (e.getId()==id)
         {
             description = e.getDescription();
         }
        }
        return description;

    }

}