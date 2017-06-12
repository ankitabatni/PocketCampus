package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

        //Country
        final AutoCompleteTextView autoCompleteCountry = (AutoCompleteTextView)findViewById(R.id.autoCompleteCountry);
        final List<String> countriesList = new ArrayList<>();
        final ArrayAdapter<String> country_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countriesList);
        autoCompleteCountry.setAdapter(country_adapter);

        //College
        final AutoCompleteTextView autoCompleteCollege = (AutoCompleteTextView)findViewById(R.id.autoCompleteCollege);
        final List<String> collegeList = new ArrayList<>();
        final List<College> collegeObjectList = new ArrayList<>();
        final ArrayAdapter<String> college_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, collegeList);
        autoCompleteCollege.setAdapter(college_adapter);

        mDatabase.child("countries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    countriesList.add(snapshot.getValue(String.class));
                }

                country_adapter.notifyDataSetChanged();
                //autoCompleteCollege.setSelection(0);
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
                            if(college.getCountryName()!=null && autoCompleteCountry.getText()!=null && !autoCompleteCountry.getText().toString().equals("") && college.getCountryName().equals(autoCompleteCountry.getText().toString())){
                                collegeList.add(college.getCollegeName());
                            }
                        }

                        college_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

            }
        });

        autoCompleteCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoCompleteCollege.setText("");

                String country_selected = autoCompleteCountry.getText().toString();
                Log.d("Country Selected", country_selected);
                college_adapter.clear();
                for(College college : collegeObjectList){
                    if(college.getCountryName()!=null && college.getCountryName().equals(country_selected)){
                        college_adapter.add(college.getCollegeName());
                    }
                }
            }
        });

    }

    public void onButtonClick(View v) {

        if (v.getId() == R.id.signup1) {
            Intent i1 = new Intent(this, Signup2.class);
            AutoCompleteTextView autoCompleteCountry = (AutoCompleteTextView)findViewById(R.id.autoCompleteCountry);
            String country = autoCompleteCountry.getText().toString();
            AutoCompleteTextView autoCompleteCollege = (AutoCompleteTextView)findViewById(R.id.autoCompleteCollege);
            String college = autoCompleteCollege.getText().toString();
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
