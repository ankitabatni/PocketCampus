package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IndividualEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_event);
    }
    public void onClick(View v) {

        if (v.getId() == R.id.IndividualButton) {
            Intent intent = new Intent(this, PostEvent.class);
            startActivity(intent);
        }

    }
}
