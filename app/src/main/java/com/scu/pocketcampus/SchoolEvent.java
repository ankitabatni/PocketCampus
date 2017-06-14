package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.scu.pocketcampus.adapter.EventListAdapter;
import com.scu.pocketcampus.model.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SchoolEvent extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_event);

        final ArrayList<Event> eventList = new ArrayList<>();
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
                    eventListAdapter.add(snapshot.getValue(Event.class));
                }

                for(Event event : eventList){
                    Log.d("event name", event.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClick(View v) {

        if (v.getId() == R.id.SchoolButton) {
            Intent intent = new Intent(this, PostEvent.class);
            startActivity(intent);
        }

    }
}
