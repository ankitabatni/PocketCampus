package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ankita.pocketcampus.model.Professor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RateProfSearch extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_search_prof);
        setTitle("RateProfSearch Professor");
        final  Intent intent = new Intent(this,ViewProfessor.class);
        final List<Professor> profObjectList = new ArrayList<>();
        final List<String> profNameList = new ArrayList<String>();
        final ListView listview = (ListView) findViewById(R.id.profListView);
        final ArrayAdapter<String> prof_adapter = new ArrayAdapter<String>(this,  R.layout.my_list, profNameList);
        listview.setAdapter(prof_adapter);
        mDatabase.child("professors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    profObjectList.add(snapshot.getValue(Professor.class));
                }
                for(Professor prof : profObjectList){
                    profNameList.add(prof.getFirstName()+" "+prof.getLastName());
                    Log.i("Prof Name : ",prof.getFirstName());
                }

               prof_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                String selectedprof=profNameList.get(position);
                for(Professor prof : profObjectList){
                  String name =  prof.getFirstName()+" "+prof.getLastName();
                    if(name.equals(selectedprof)){
                        intent.putExtra("Prof Object", (Parcelable) prof);
                    }
                };
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Prof Selected : "+selectedprof,   Toast.LENGTH_LONG).show();
            }
        });

    }


    public void onClick(View v) {


        if (v.getId() == R.id.add_professor_button) {
            Intent i = new Intent(this, AddProfessor.class);
            startActivity(i);
        }
    }
}
