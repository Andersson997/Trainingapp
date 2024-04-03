package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText anamn, lord, fnamn, enamn;
Button registrera;
User user;
Boolean added;
Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registrera = findViewById(R.id.registrera);
        registrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                anamn = findViewById(R.id.anamn);
                lord = findViewById(R.id.lord);
                fnamn = findViewById(R.id.fnamn);
                enamn = findViewById(R.id.enamn);
                added = true;
                if (anamn.getText().toString().isEmpty()||lord.getText().toString().isEmpty()||fnamn.getText().toString().isEmpty()||enamn.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Vänligen fyll i alla fälten", Toast.LENGTH_SHORT).show();
                    added = false;
                }
                else
                {
                    for (User e:db.listOfAllUser())
                    {
                        if (anamn.getText().toString().equals(e.getAnamn()))
                        {
                            Toast.makeText(Register.this, "Användaren finns redan", Toast.LENGTH_SHORT).show();
                            added = false;
                        }
                    }
                }

                if (added)
                {
                    user = new User(anamn.getText().toString(), lord.getText().toString(), fnamn.getText().toString(), enamn.getText().toString());
                    Database myDB = new Database(Register.this);
                    myDB.addUser(user);
                    Intent nextpage = new Intent(Register.this, Login.class);
                    startActivity(nextpage);
                }


            }
        });
    }
}