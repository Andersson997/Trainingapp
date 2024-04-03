package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    int count;
    Button countbtn;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setClickListener();
        TextView Fnamn = (TextView) findViewById(R.id.FNamn);
        TextView Enamn = (TextView) findViewById(R.id.ENamn);
        LoadSavedSetting();
        Fnamn.setText(((CurrentlyLoggedInUser)getApplication()).getUser().getFnamn());
        Enamn.setText(((CurrentlyLoggedInUser)getApplication()).getUser().getEnamn());

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.mittschema:
                        startActivity(new Intent(getApplicationContext(), MySchedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profil:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
    private void setClickListener() {
        countbtn = findViewById(R.id.count);
        reset = findViewById(R.id.reset);
        countbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveSetting();
                LoadSavedSetting();
            }

        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                count=0;
                countbtn.setText("0");
            }
        });

    }
    private void SaveSetting(){
        SharedPreferences settings = getSharedPreferences("MySettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        count = count +1 ;
        String counttext = Integer.toString(count);
        editor.putString("oldCount",counttext );
        editor.commit();
    }
    private void LoadSavedSetting(){
        SharedPreferences settings = getSharedPreferences("MySettings", MODE_PRIVATE);

        String counttext = Integer.toString(count);
        String oldCount = settings.getString("oldCount", counttext);
        Button countbtn = findViewById(R.id.count);
        countbtn.setText(oldCount);
        count = Integer.parseInt(oldCount);

    }
}



