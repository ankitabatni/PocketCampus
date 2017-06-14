package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

public class EventScreen extends AppCompatActivity {

    public static HashMap<String, String> eventTitleMap = new HashMap<String, String>();
    public static HashMap<String, String> eventNameMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setTitle("Events");

        //Title lookup
        eventNameMap.put("School Events", "school_event");
        eventNameMap.put("Community Events", "community_event");
        eventNameMap.put("Free Food Events", "free_food_event");
        eventNameMap.put("Individual Events", "individual_event");

        //Name look up
        eventTitleMap.put("school_event", "School Events");
        eventTitleMap.put("community_event", "Community Events");
        eventTitleMap.put("free_food_event", "Free Food Events");
        eventTitleMap.put("individual_event", "Individual Events");
    }

    public void onClick(View v){

        if(v.getId() == R.id.buttonC) {
            Intent intent=new Intent(this,SchoolEvent.class);
            intent.putExtra("eventName", "community_event");
            startActivity(intent);
        }


        if(v.getId()==R.id.buttonF) {
            Intent i = new Intent(this, SchoolEvent.class);
            i.putExtra("eventName", "free_food_event");
            startActivity(i);
        }

        if(v.getId()==R.id.buttonS) {
            Intent i = new Intent(this, SchoolEvent.class);
            i.putExtra("eventName", "school_event");
            startActivity(i);
        }

        if(v.getId()==R.id.buttonI) {
            Intent i = new Intent(this, SchoolEvent.class);
            i.putExtra("eventName", "individual_event");
            startActivity(i);
        }
    }

}
