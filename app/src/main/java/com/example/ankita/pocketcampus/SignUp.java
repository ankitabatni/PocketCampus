package com.example.ankita.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ankita.pocketcampus.model.SharedUser;

public class SignUp extends AppCompatActivity  {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Sign Up Here - Step 1/3");

        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                // Respond to the action bar's Up/Home button
                case android.R.id.home:
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        public  void onClickNext (View v){

            if(v.getId()==R.id.accept){

                EditText firstname = (EditText)findViewById(R.id.firstname);
                EditText lastname = (EditText)findViewById(R.id.lastname);
                String firstnamestr = firstname.getText().toString();
                String lastnamestr = lastname.getText().toString();

                Intent i = new Intent(this, Signup1.class);
                SharedUser user = SharedUser.createFreshUser();
                user.setFirstName(firstnamestr);
                user.setLastName(lastnamestr);
                startActivity(i);
               /* EditText uname = (EditText)findViewById(R.id.uname);
                EditText email = (EditText)findViewById(R.id.email);
                EditText pass1 = (EditText)findViewById(R.id.pass1);
                EditText pass2 = (EditText)findViewById(R.id.pass2);

                String namestr = firstname.getText().toString();
                String unamestr = uname.getText().toString();
                String emailstr = email.getText().toString();
                String pass1str = pass1.getText().toString();
                String pass2str = pass2.getText().toString();

                if( namestr.isEmpty() || unamestr.isEmpty() || emailstr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty()){
                    TextView tv = (TextView)findViewById(R.id.textView2);
                    tv.setText("Please fill all the fields");
                }
                else if(!pass1str.equals(pass2str)){
                    TextView tv = (TextView)findViewById(R.id.textView2);
                    tv.setText("Password do not match, Please check");
                }
                else {
                    //Intent i = new Intent(this, SignUp1.class);
                    //startActivity(i);
                }
            */
            }
        }


    }