package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewProfessor extends AppCompatActivity {

    private Button rateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_professor);

    }

     public void onClick(View v){
         if (v.getId() == R.id.rate_button) {
             Intent intent = new Intent(this, RateProfessor.class);
             startActivity(intent);
         }
     }
}
