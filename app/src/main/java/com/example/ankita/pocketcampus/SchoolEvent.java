package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SchoolEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_event);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.SchoolButton) {
            Intent intent = new Intent(this, PostEvent.class);
            startActivity(intent);
        }

    }
}
