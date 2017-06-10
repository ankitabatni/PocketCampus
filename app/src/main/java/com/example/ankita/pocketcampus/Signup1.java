package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ankita.pocketcampus.model.College;
import com.example.ankita.pocketcampus.model.SharedUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ankita.pocketcampus.R.id.spinnerCollege;
import static com.example.ankita.pocketcampus.R.id.spinnerCountry;

/**
 * Created by ankita on 5/5/17.
 */


public class Signup1 extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);
        setTitle("Sign Up Here - Step 2/3");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Spinner spinner_country = (Spinner)findViewById(spinnerCountry);
        final List<String> countriesList = new ArrayList<>();
        final ArrayAdapter<String> country_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countriesList);
        spinner_country.setAdapter(country_adapter);
        final Spinner spinner_college = (Spinner)findViewById(spinnerCollege);
        final List<String> collegeList = new ArrayList<>();
        final List<College> collegeObjectList = new ArrayList<>();
        final ArrayAdapter<String> college_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, collegeList);
        spinner_college.setAdapter(college_adapter);

        mDatabase.child("countries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    countriesList.add(snapshot.getValue(String.class));
                }

                country_adapter.notifyDataSetChanged();
                spinner_college.setSelection(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("colleges").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                        for(DataSnapshot snapshot : snapshots){
                            collegeObjectList.add(snapshot.getValue(College.class));
                        }

                        for(College college : collegeObjectList){
                            if(college.getCountryName()!=null && college.getCountryName().equals(spinner_country.getSelectedItem().toString())){
                                collegeList.add(college.getCollegeName());
                            }
                        }

                        college_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

            }
        });


        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int spinner_country_position =  -1;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner_country_position == -1 || spinner_country_position != position){
                    spinner_country_position = spinner_country.getSelectedItemPosition();
                    String country_selected = spinner_country.getSelectedItem().toString();
                    collegeList.clear();
                    for(College college : collegeObjectList){
                        if(college.getCountryName()!=null && college.getCountryName().equals(country_selected)){
                            collegeList.add(college.getCollegeName());
                        }
                    }

                    college_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onButtonClick(View v) {

        if (v.getId() == R.id.signup1) {
            Intent i1 = new Intent(this, Signup2.class);

            Spinner spinner_country = (Spinner)findViewById(spinnerCountry);
            String country = spinner_country.getSelectedItem().toString();
            Spinner spinner_college = (Spinner)findViewById(spinnerCollege);
            String college = spinner_college.getSelectedItem().toString();
            SharedUser.getInstance().setCollege(college);
            SharedUser.getInstance().setCountry(country);
            startActivity(i1);

        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
