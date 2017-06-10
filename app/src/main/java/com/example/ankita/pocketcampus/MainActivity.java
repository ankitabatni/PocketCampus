package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pocket Campus");



    }


    public void onClick(View v){

        if(v.getId() == R.id.button1) {
                Intent intent=new Intent(this,LoginScreen.class);
                startActivity(intent);
            }


        if(v.getId()==R.id.button2) {
            Intent i = new Intent(this, SignUp.class);
                startActivity(i);
            }
        }
    }


