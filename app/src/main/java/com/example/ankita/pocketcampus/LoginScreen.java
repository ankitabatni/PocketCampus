package com.example.ankita.pocketcampus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

        private Button buttonSignIn,buttonForgotPassword;
        private EditText editTextEmail;
        private EditText editTextPass;

        private FirebaseAuth firebaseAuth;
        private DatabaseReference mDatabase;

        private ProgressDialog progressDialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_screen);
            setTitle("Pocket Campus Login");

            firebaseAuth=FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();

            editTextEmail=(EditText)findViewById(R.id.TFUsername);
            editTextPass=(EditText)findViewById(R.id.TFPassword);
            buttonSignIn=(Button)findViewById(R.id.login);
            buttonForgotPassword = (Button)findViewById(R.id.forgot_password);

            progressDialog=new ProgressDialog(this);

            buttonSignIn.setOnClickListener(this);
            buttonForgotPassword.setOnClickListener(this);


        }

        private void userLogin(){
            String email=editTextEmail.getText().toString().trim();
            String password=editTextPass.getText().toString().trim();
            if(TextUtils.isEmpty(email)){
                //email is empty
                Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
                //stopping function from executing further
                return;

            }
            if(TextUtils.isEmpty(password)){
                //password is empty
                Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
                //stopping function from executing further
                return;
            }
            progressDialog.setMessage("Logging in User...");
            progressDialog.show();


            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if(task.isSuccessful() ){
                                if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    //start the activity_homescreen
                                    finish();
                                    //updateFirstName(firebaseAuth.getCurrentUser().getUid(), "Avinash 1");
                                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                }
                                else{
                                    startActivity(new Intent(getApplicationContext(), VerifyEmail.class));
                                }
                            }else{
                                Toast.makeText(LoginScreen.this,"Invalid username/password! Please try " +
                                                "again..",
                                        Toast
                                                .LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    private void updateFirstName(String userId, String firstName) {
        mDatabase.child("users").child(userId).child("firstName").setValue(firstName);
    }

    private void resetPassword(){
       // FirebaseAuth auth = FirebaseAuth.getInstance();
        String email=editTextEmail.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            //stopping function from executing further
            return;

        }
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),ResetPassword.class));
                        }
                    }
                });
    }

        @Override
        public void onClick(View v) {
            if(v==buttonSignIn){
                userLogin();
            }
            if (v == buttonForgotPassword){
                resetPassword();
            }

        }
    }
