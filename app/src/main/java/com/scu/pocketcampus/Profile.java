package com.scu.pocketcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scu.pocketcampus.model.User;

import static com.scu.pocketcampus.R.id.profileLastName;

public class Profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final TextView profileFirstNameString = (TextView) findViewById(R.id.profileFirstName);
        final TextView profileLastNameString = (TextView)findViewById(profileLastName);
        final TextView profileEmailString = (TextView) findViewById(R.id.profileEmail);
        final TextView profileCountryString = (TextView)findViewById(R.id.profileCountry);
        final TextView profileCollegeString = (TextView)findViewById(R.id.profileCollege);
        mDatabase.child("users").child(firebaseUser.getUid().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user  = dataSnapshot.getValue(User.class);
                profileFirstNameString.setText(user.getFirstName().toUpperCase());
                profileLastNameString.setText(user.getLastName().toUpperCase());
                profileEmailString.setText(user.getEmail());
                profileCountryString.setText(user.getCountry().toUpperCase());
                profileCollegeString.setText(user.getCollege().toUpperCase());
                setTitle(user.getFirstName().toUpperCase() + " 's Profile");
            }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }
}
