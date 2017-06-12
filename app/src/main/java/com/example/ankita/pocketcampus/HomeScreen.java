package com.example.ankita.pocketcampus;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
     * Created by ankita on 5/10/17.
     */

    public class HomeScreen extends AppCompatActivity {

        //private static Button button3_sbm, button4_sbm;
        private Menu mainMenu;
        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_homescreen);
            setTitle("Pocket Campus");
            firebaseAuth = FirebaseAuth.getInstance();
            //OnClickButtonListener();
        }

        public void onClick(View v) {
                    if (v.getId() == R.id.button_event) {
                        Intent intent = new Intent(this, Event.class);
                        startActivity(intent);
                    }
                    if (v.getId() == R.id.button_rate_prof) {
                        Intent intent = new Intent(this, SearchProfessor.class);
                        startActivity(intent);
                    }
                    if (v.getId() == R.id.button_chat) {
                    Intent intent = new Intent(this, Chat.class);
                    startActivity(intent);
            }
            if(v.getId()==R.id.buttonLocate)
            {
                Intent i2=new Intent(this,Location.class);
                startActivity(i2);
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




