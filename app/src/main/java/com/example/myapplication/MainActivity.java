package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity
{
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setClickListener();
        //Nedanför är koden för navigeringsbaren som finns på alla sidor utom loginsidan och registreringssidan
        //Den dyker upp först på denna sida
        // Kod för navigationsbar hämtad från https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch(item.getItemId())
                {
                    case R.id.mittschema:
                        startActivity(new Intent(getApplicationContext(), MySchedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        ArrayList<Schedule> schedulelist = new ArrayList<Schedule>();

        Schedule ppl = new Schedule(1, "PPL(push pull legs)");
        Schedule upperlower = new Schedule(2,"Upper/Lower");
        Schedule bro_split = new Schedule(3, "Bro Split");
        Schedule total_body = new Schedule(4, "Total body");
        schedulelist.add(ppl);
        schedulelist.add(upperlower);
        schedulelist.add(bro_split);
        schedulelist.add(total_body);
        ArrayList<Excercise> excerciselist = new ArrayList<Excercise>();

        Excercise deadlift = new Excercise(1, "Marklyft", "Börja med att stå så att stången är i linje med fotens mittpunkt stå höftbrett, Böj dig ner och greppa stången strax utanför smalbenen, andas in och spänn hela kroppen och lyft stången genom att räta ut knän och höft. Drag stången nära kroppen ");
        Excercise pullups = new Excercise(2, "Pullups", "Greppa stången ovanför dig lite bredare än axel brett, dra ner axlarna och spänn ryggen, dra dig upp tills hakan är över stången utan att böja på nacken");
        Excercise sittanderodd = new Excercise(3, "Sittande rodd", "Välj ett smalt handtag, sätt dig så att du är rak i ryggen och spänn bålen, drag handtaget mot dig så långt du kan utan röra på överkroppen släpp tillbaka handtaget till startpositionen utan att röra på överkroppen");
        Excercise incline_curls = new Excercise(4, "Incline curls", "Välj en bänk som går att justera lutningen på, prova vilken grad av lutning som känns bäst, använd hantlar och curla vikterna upp och ner kontrollerat");
        Excercise hammer_curls = new Excercise(5, "Hammer curls", "Stå upp med hantlar i händerna, använd ett neutralt grepp och curla vikterna upp och ner kontrollerat");
        Excercise leg_raises = new Excercise(6, "Leg raises", "Lägg dig ner eller häng från en stång beroende på styrkenivå, lyft benen med böjda eller raka ben beroende på styrkenivå, kontrollera rörelsen upp och ner utan att svinga");
        Excercise benchpress = new Excercise(7, "Bänkpress", "Lägg dig ner på en platt bänk och dra bak och ner axlarna samtidigt som du drar in fötterna mot dig, greppa stången bredare än axelbredden men kan justeras efter vad som känns bäst, andas in och lyft stången, sänk ner kontrollerat till den lägre delen av bröstbenet");
        Excercise incline_dumbbellpress = new Excercise(8, "Incline hantelpress", "Välj en bänk som går att justera lutningen på, ställ in bänken så att det är 30 graders lutning uppåt, om det inte står grader på bänken ställ den så att den lutar uppåt men på den lägsta inställningen, dra tillbaka och ner axlarna, andas in, sänk ner hantlarna kontrollerat så att armbågarna inte hamnar parallellt med axlarna lyft hantlarna uppåt igen och repetera");
        Excercise cable_flys = new Excercise(9, "Cable flys","Ställ dig mellan två kabelmaskiner och ställ in båda på den högsta inställningen, greppa handtagen och dra dem kontrollerat mot höften med lätt böjda armar släpp tillbaka vikterna sakta och repetera");
        Excercise shrugs = new Excercise(10,"shrugs","Denna övning kan göras med både hantlar och en stång beroende på vad som känns bäst, greppa den vikt du vill använda och håll dem mot sidorna ståendes, lyft axlarna kontrollerat så högt du kan utan att böja på armarna sänk axlarna igen och repetera");
        Excercise lateral_raises = new Excercise(11, "Lateral raises", "Håll ett par hantlar vid dina sidor ståendes, luta dig lite framåt, böj lätt på armarna, lyft armarna ifrån dig kontrollerat tills händerna är parallella med axlarna sänk ner vikterna sakta och repetera");
        Excercise face_pulls = new Excercise(12,"Face pulls","Använd en kabelmaskin ställ in den på den högsta inställningen använd ett rep, inled övningen med att ställa dig stadigt och dra repet mot ansiktet och händerna ifrån ansiktet, håll armbågarna parallellt med axlarna, släpp tillbaka vikterna sakta och repetera");
        excerciselist.add(deadlift);
        excerciselist.add(pullups);
        excerciselist.add(sittanderodd);
        excerciselist.add(incline_curls);
        excerciselist.add(hammer_curls);
        excerciselist.add(leg_raises);
        excerciselist.add(benchpress);
        excerciselist.add(incline_dumbbellpress);
        excerciselist.add(cable_flys);
        excerciselist.add(shrugs);
        excerciselist.add(lateral_raises);
        excerciselist.add(face_pulls);
        schedulelist.get(0).getExcerciseArrayList().add(deadlift);
        schedulelist.get(0).getExcerciseArrayList().add(pullups);
        schedulelist.get(0).getExcerciseArrayList().add(sittanderodd);
        schedulelist.get(0).getExcerciseArrayList().add(incline_curls);
        schedulelist.get(0).getExcerciseArrayList().add(hammer_curls);
        schedulelist.get(0).getExcerciseArrayList().add(leg_raises);
        schedulelist.get(1).getExcerciseArrayList().add(benchpress);
        schedulelist.get(1).getExcerciseArrayList().add(incline_dumbbellpress);
        schedulelist.get(1).getExcerciseArrayList().add(cable_flys);
        schedulelist.get(1).getExcerciseArrayList().add(shrugs);
        schedulelist.get(1).getExcerciseArrayList().add(lateral_raises);
        schedulelist.get(1).getExcerciseArrayList().add(face_pulls);

        db = new Database(MainActivity.this);
        db.addHardCodedExcercise(excerciselist);
        db.addHardcodedSchedule(schedulelist);
    }
    private void setClickListener() {
        Button btn = findViewById(R.id.traningsschema);
        Button btn1 = findViewById(R.id.kalender);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent schemaSida = new Intent(MainActivity.this, ScheduleOption.class);
                startActivity(schemaSida);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Koden för att starta telefonens kalender och skapa en händelse https://developer.android.com/guide/topics/providers/calendar-provider
                int Ar = cal.get(Calendar.YEAR);
                int Manad = cal.get(Calendar.MONTH);
                int Dag = cal.get(Calendar.DAY_OF_MONTH);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(Ar, Manad, Dag, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(Ar, Manad, Dag, 8, 30);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "Träning")
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Styrketräning")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
                startActivity(intent);
            }
        });
    }
}

