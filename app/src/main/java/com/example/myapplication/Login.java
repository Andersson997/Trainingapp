package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity
{
    Database db = new Database(this);
    boolean fail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setClickListener();
    }

    private void setClickListener() {
        Button loginbtn = findViewById(R.id.login);
        Button registerbtn = findViewById(R.id.register);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText username = (EditText)findViewById(R.id.usernameinput);
                EditText password = (EditText)findViewById(R.id.passwordinput);

                fail=true;
                for (User e:db.listOfAllUser())
                {
                    if (username.getText().toString().equals(e.getAnamn()) && password.getText().toString().equals(e.getLord()))
                    {
                        fail =false;
                        ((CurrentlyLoggedInUser)getApplication()).setUser(e);
                        Intent nextpage = new Intent(Login.this, MainActivity.class);
                        startActivity(nextpage);
                    }
                }
                if (fail)
                {
                    Toast.makeText(Login.this, "Felaktiga inloggningsuppgifter", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent nextpage = new Intent(Login.this, Register.class);
                startActivity(nextpage);
            }
        });
    }
}
