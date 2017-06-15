package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.scu.pocketcampus.adapter.EventListAdapter;
import com.scu.pocketcampus.model.PocketCampusEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scu.pocketcampus.model.Professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class SchoolEvent extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String eventName = "";

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, EventScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        eventName = (String) extras.get("eventName");
        setTitle(getScreenTitle(eventName));
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_event);

        final ArrayList<PocketCampusEvent> eventList = new ArrayList<>();
        // Create the adapter to convert the array to views
        final EventListAdapter eventListAdapter = new EventListAdapter(this, eventList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.schoolEventListView);
        listView.setAdapter(eventListAdapter);


        mDatabase.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventListAdapter.clear();
                Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    PocketCampusEvent event = snapshot.getValue(PocketCampusEvent.class);
                    if(eventName!="" && event.getType().equals(eventName)){
                        eventListAdapter.add(event);
                    }
                }

                eventListAdapter.sort(new Comparator<PocketCampusEvent>() {
                    @Override
                    public int compare(PocketCampusEvent pocketCampusEvent, PocketCampusEvent t1) {
                        Date date1 = null;
                        Date date2 = null;
                        try {
                             date1 = new SimpleDateFormat("mm/dd/yyyy").parse(pocketCampusEvent.getDate());
                             date2 = new SimpleDateFormat("mm/dd/yyyy").parse(t1.getDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return date1.compareTo(date2);
                    }
                });

                for(PocketCampusEvent event : eventList){
                    Log.d("event name", event.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String getScreenTitle(String eventName){
        return EventScreen.eventTitleMap.get(eventName);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.SchoolButton) {
            Intent intent = new Intent(this, PostEvent.class);
            startActivity(intent);
        }

    }
}
