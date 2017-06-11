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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ankita.pocketcampus.model.Professor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchProfessor extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_professor);
        setTitle("SearchProfessor Professor");
        final  Intent intent = new Intent(this,ViewProfessor.class);
        final List<Professor> profObjectList = new ArrayList<>();
        final List<String> profNameList = new ArrayList<String>();
        final List<String> profNameFilteredList = new ArrayList<String>();
        final ListView listview = (ListView) findViewById(R.id.profListView);
        final ArrayAdapter<String> prof_adapter = new ArrayAdapter<String>(this,  R.layout.my_list, profNameFilteredList);
        listview.setAdapter(prof_adapter);

        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Type professor name here");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                profNameFilteredList.clear();

                if(newText!=null && !newText.equals("") && newText.length() > 2){
                    for(String profName : profNameList){
                        if(profName.toLowerCase().contains(newText.toLowerCase())){
                            profNameFilteredList.add(profName);
                        }
                    }
                }

                prof_adapter.notifyDataSetChanged();
                //prof_adapter.getFilter().filter(newText);
                return false;
            }
        });


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
                String selectedprof=profNameFilteredList.get(position);
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
