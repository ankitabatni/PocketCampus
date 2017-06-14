package com.scu.pocketcampus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scu.pocketcampus.model.SharedUser;
import com.scu.pocketcampus.model.User;

public class Signup2 extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        setTitle("Sign Up Here - Step 3/3");

        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog= new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonRegister = (Button)findViewById(R.id.signup);
        editTextEmail=(EditText)findViewById(R.id.email);
        editTextPass=(EditText)findViewById(R.id.pass);
        buttonRegister.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        if(v.getId()==R.id.signup){

            EditText firstname = (EditText)findViewById(R.id.email);
            EditText lastname = (EditText)findViewById(R.id.pass);
            String firstnamestr = firstname.getText().toString();
            String lastnamestr = lastname.getText().toString();
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

    private void registerUser(){
        if(editTextEmail.getText()==null)
            Log.d("email","Its null" );
        else
            Log.d("email",editTextEmail.getText().toString() );
        String email1=editTextEmail.getText().toString().trim();
        String password=editTextPass.getText().toString().trim();
        if(TextUtils.isEmpty(email1)){
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
        //if validations are okay.
        //we will first show a progressbar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email1,password)
                .addOnCompleteListener(Signup2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //user is successfully created.
                            Toast.makeText(Signup2.this,"Registered Successfully",Toast
                                    .LENGTH_SHORT).show();

                            finish();

                            startActivity(new Intent(getApplicationContext(),VerifyEmail.class));
                            writeNewUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            sendEmailVerification();

                        }else{
                            Toast.makeText(Signup2.this,"Could not reigster! Please try again..",
                                    Toast
                                            .LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void writeNewUser(String userId) {
        User user = new User();
        user.userId = userId;
        user.firstName = SharedUser.getInstance().getFirstName();
        user.lastName = SharedUser.getInstance().getLastName();
        user.email = editTextEmail.getText().toString().trim();
        user.country = SharedUser.getInstance().getCountry();
        user.college = SharedUser.getInstance().getCollege();
        mDatabase.child("users").child(userId).setValue(user);
    }

    private void sendEmailVerification() {
        // Disable button
       // findViewById(R.id.SignUp).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                      // findViewById(R.id.SignUp).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(Signup2.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(Signup2.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
    @Override
    public void onClick(View v) {
        if (v==buttonRegister){
            registerUser();
        }

    }


}