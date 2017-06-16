package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
     * Created by ankita on 5/10/17.
     */

    public class HomeScreen extends MainActivity {

        //private static Button button3_sbm, button4_sbm;


        @Override
        public void onBackPressed() {
            
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_homescreen);
            setTitle("Pocket Campus");
            //OnClickButtonListener();
        }

        public void onClick(View v) {
                    if (v.getId() == R.id.button_event) {
                        Intent intent = new Intent(this, EventScreen.class);
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




    }




