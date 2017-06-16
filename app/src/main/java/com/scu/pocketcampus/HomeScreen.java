package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;

/**
     * Created by ankita on 5/10/17.
     */

    public class HomeScreen extends AppCompatActivity {
    private Menu mainMenu;
    private FirebaseAuth firebaseAuth;
    private ViewFlipper myViewFlipper;
    private float initialXPoint;
    int[] image = { R.drawable.one_full, R.drawable.two_full,
            R.drawable.three_full, R.drawable.four_full, R.drawable.five_full,
            R.drawable.six_full, R.drawable.seven_full, R.drawable.eight_full,
            R.drawable.nine_full, R.drawable.ten_full ,R.drawable.eleven_full, R.drawable.twelve_full };
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
        //private static Button button3_sbm, button4_sbm;


        @Override
        public void onBackPressed() {

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_homescreen);
            setTitle("Pocket Campus");
            myViewFlipper = (ViewFlipper) findViewById(R.id.myflipper);

            for (int i = 0; i < image.length; i++) {
                ImageView imageView = new ImageView(HomeScreen.this);
                imageView.setImageResource(image[i]);
                myViewFlipper.addView(imageView);
            }
            myViewFlipper.setAutoStart(true);
            myViewFlipper.setFlipInterval(2000);
            myViewFlipper.startFlipping();
        }

        public void onClick(View v) {
                    if (v.getId() == R.id.button_event) {
                        Intent intent = new Intent(this, EventHome.class);
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
                startActivity(new Intent(this, Profile.class));
                return true;
            case R.id.about:
                startActivity(new Intent(this, About.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    }




