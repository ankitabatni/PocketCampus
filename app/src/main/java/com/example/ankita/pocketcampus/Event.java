package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setTitle("Events");
    }

    public void onClick(View v){

        if(v.getId() == R.id.buttonC) {
            Intent intent=new Intent(this,CommunityEvent.class);
            startActivity(intent);
        }


        if(v.getId()==R.id.buttonF) {
            Intent i = new Intent(this, FreeFoodEvent.class);
            startActivity(i);
        }

        if(v.getId()==R.id.buttonS) {
            Intent i = new Intent(this, SchoolEvent.class);
            startActivity(i);
        }

        if(v.getId()==R.id.buttonI) {
            Intent i = new Intent(this, IndividualEvent.class);
            startActivity(i);
        }
    }
}
