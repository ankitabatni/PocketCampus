package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ankita.pocketcampus.model.Professor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import static com.example.ankita.pocketcampus.R.id.profName;

public class AddProfessor extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_professor);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addProfessor() {
        EditText firstName = (EditText) findViewById(profName);
        EditText lastName = (EditText) findViewById(R.id.profLastName);
        EditText schoolName = (EditText) findViewById(R.id.NameOfSchool);
        //EditText departmentName = (EditText)findViewById(R.id.profLastName);
        String firstnamestr = firstName.getText().toString();
        String lastnamestr = lastName.getText().toString();
        String schoolNamestr = schoolName.getText().toString();
        // String departmentNamestr = departmentName.getText().toString();
        Professor prof = new Professor(UUID.randomUUID().toString(), schoolNamestr, firstnamestr, lastnamestr, "Computer Science");
        mDatabase.child("professors").child(prof.getProfessorId().toString()).setValue(prof);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.submit_buttton) {
            addProfessor();
            Toast.makeText(AddProfessor.this,"Professor Added Successfully",Toast
                    .LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewProfessor.class);
            startActivity(intent);
        }
    }
}
