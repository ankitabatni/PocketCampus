package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FreeFoodEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_food_event);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.FreeFoodButton) {
            Intent intent = new Intent(this, PostEvent.class);
            startActivity(intent);
        }

    }
}
