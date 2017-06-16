package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Menu mainMenu;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pocket Campus");
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void onClick(View v){

        if(v.getId() == R.id.button1) {
                Intent intent=new Intent(this,LoginScreen.class);
                startActivity(intent);
            }


        if(v.getId()==R.id.button2) {
            Intent i = new Intent(this, SignUp.class);
                startActivity(i);
            }
        }


    }


