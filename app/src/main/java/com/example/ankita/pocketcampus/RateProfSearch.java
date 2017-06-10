package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RateProfSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_search_prof);
        setTitle("RateProfSearch Professor");
    }

    public void onClick(View v) {


        if (v.getId() == R.id.add_professor_button) {
            Intent intent = new Intent(this, AddProfessor.class);
            startActivity(intent);
        }
    }
}
