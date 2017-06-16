package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.scu.pocketcampus.model.College;
import com.scu.pocketcampus.model.Professor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddProfessor extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Professor prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_professor);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //College
        final AutoCompleteTextView autoCompleteCollege = (AutoCompleteTextView)findViewById(R.id.autoCompleteCollege);
        final List<College> collegeObjectList = new ArrayList<>();
        final List<String> collegeList = new ArrayList<>();
        final ArrayAdapter<String> college_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, collegeList);
        autoCompleteCollege.setAdapter(college_adapter);

        //Department
        final AutoCompleteTextView autoCompleteDepartment = (AutoCompleteTextView)findViewById(R.id.autoCompleteDepartment);
        final List<String> departmentList = new ArrayList<>();
        final ArrayAdapter<String> department_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, departmentList);
        autoCompleteDepartment.setAdapter(department_adapter);

        mDatabase.child("departments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                department_adapter.clear();
                Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    department_adapter.add(snapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("colleges").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collegeObjectList.clear();
                college_adapter.clear();
                Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    College college = snapshot.getValue(College.class);
                    collegeObjectList.add(college);
                    college_adapter.add(college.getCollegeName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addProfessor() {
        EditText firstName = (EditText) findViewById(R.id.profName);
        EditText lastName = (EditText) findViewById(R.id.profLastName);
        AutoCompleteTextView autoCompleteCollege = (AutoCompleteTextView) findViewById(R.id.autoCompleteCollege);
        AutoCompleteTextView autoCompleteDepartment = (AutoCompleteTextView) findViewById(R.id.autoCompleteDepartment);
        //EditText departmentName = (EditText)findViewById(R.id.profLastName);
        String firstnamestr = firstName.getText().toString();
        String lastnamestr = lastName.getText().toString();
        String schoolNamestr = autoCompleteCollege.getText().toString();
        String departmentNamestr = autoCompleteDepartment.getText().toString();
        if (firstnamestr.isEmpty() || lastnamestr.isEmpty()) {
            Toast.makeText(this, "Please provide the first/last name", Toast.LENGTH_SHORT).show();
        } else if (schoolNamestr.isEmpty()) {
            Toast.makeText(this, "Please provide school name", Toast.LENGTH_SHORT).show();
        } else if (departmentNamestr.isEmpty()) {
            Toast.makeText(this, "Please provide the department name", Toast.LENGTH_SHORT).show();
        } else {
            prof = new Professor(UUID.randomUUID().toString(), schoolNamestr, firstnamestr, lastnamestr, departmentNamestr);
            mDatabase.child("professors").child(prof.getProfessorId().toString()).setValue(prof);
            Toast.makeText(AddProfessor.this,"Professor Added Successfully",Toast
                    .LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewProfessor.class);
            intent.putExtra("Prof Object", (Parcelable) prof);
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.submit_buttton) {
            addProfessor();


        }
    }
}
