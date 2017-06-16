package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.scu.pocketcampus.model.SharedUser;

public class SignUp extends AppCompatActivity  {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Sign Up Here - Step 1/3");

        }
       /* @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                // Respond to the action bar's Up/Home button
                case android.R.id.home:
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }*/
        public  void onClickNext (View v){

            if(v.getId()==R.id.accept){

                EditText firstname = (EditText)findViewById(R.id.firstname);
                EditText lastname = (EditText)findViewById(R.id.lastname);
                String firstnamestr = firstname.getText().toString();
                String lastnamestr = lastname.getText().toString();
                if( firstnamestr.isEmpty() || lastnamestr.isEmpty()){
                    Toast.makeText(this,"Please enter first name / last name",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(this, Signup1.class);
                    SharedUser user = SharedUser.createFreshUser();
                    user.setFirstName(firstnamestr);
                    user.setLastName(lastnamestr);
                    startActivity(i);
                }

            }
        }


    }