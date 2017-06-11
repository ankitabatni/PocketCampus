package com.example.ankita.pocketcampus;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankita.pocketcampus.model.Professor;
import com.example.ankita.pocketcampus.model.Rate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RateProfessor extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private RatingBar ratingBar;
    private Float currentRating;
    private String courseName;
    private int difficulty;
    private boolean takeCourseAgain;
    private String comment;
    private Professor prof;
    public Rate rateProf;
    private CheckBox chkYes, chkNo;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        prof = (Professor) extras.getParcelable("Prof Id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);
        addListenerOnRatingBar();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        TextView profName = (TextView)findViewById(R.id.textViewProfName);
        profName.setText(prof.getFirstName() + " "+ prof.getLastName());
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        addListenerOnRatingBar();
        addListenerOnChkYes();
        addListenerOnChkNo();
        addListenerOnSeekBar();
    }

    public void addRating() {
        EditText courseNameEditText = (EditText) findViewById(R.id.course_name_editText);
        courseName = courseNameEditText.getText().toString();
        EditText commentEditText = (EditText) findViewById(R.id.editTextComments);
        comment = commentEditText.getText().toString();


        rateProf = new Rate(prof.getProfessorId(),firebaseAuth.getCurrentUser().getUid(), courseName, currentRating,difficulty,takeCourseAgain, comment);
        //Professor prof = new Professor(UUID.randomUUID().toString(), schoolNamestr, firstnamestr, lastnamestr, "Computer Science");
        mDatabase.child("rating").child(UUID.randomUUID().toString()).setValue(rateProf);
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating = rating;
            }
        });
    }
    public void addListenerOnSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                progress = progresValue;

                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();

            }


            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();

            }


            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {

                //textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
                difficulty = progress;
            }

        });

    }


    public void addListenerOnChkYes() {

        chkYes = (CheckBox) findViewById(R.id.checkBox);

        chkYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                  takeCourseAgain = true;
                }

            }
        });

    }
    public void addListenerOnChkNo() {

        chkNo = (CheckBox) findViewById(R.id.checkBox2);

        chkNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    takeCourseAgain = false;
                }

            }
        });

    }

    public void onClick(View v){
        if (v.getId() == R.id.submitRating_button) {
            addRating();
            Toast.makeText(getApplicationContext(), "Thank You for your rating ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewProfessor.class);
            intent.putExtra("Prof Object", (Parcelable) prof);
            startActivity(intent);
        }
    }
}
