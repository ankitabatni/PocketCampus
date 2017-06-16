package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mainMenu=menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(this, LoginScreen.class));
                return true;
            case R.id.user_account:
                startActivity(new Intent(this, Account.class));
                return true;
            case R.id.about:
                startActivity(new Intent(this, About.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }


