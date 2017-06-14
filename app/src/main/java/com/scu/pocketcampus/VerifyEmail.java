package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.scu.pocketcampus.R.id.resend_mail;

public class VerifyEmail extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private static final String TAG = "EmailPassword";

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        Button buttonResend = (Button)findViewById(R.id.resend_mail);
        buttonResend.setOnClickListener(this);
    }

    private void sendEmailVerification() {
        // Disable button
        //findViewById(R.id.resend_mail).setEnabled(false);
        mAuth = FirebaseAuth.getInstance();

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        //findViewById(R.id.resend_mail).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyEmail.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(VerifyEmail.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    public void onButtonClick(View v) {

        if (v.getId() == R.id.login_after_verification) {
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
    }


    @Override
    public void onClick(View v) {
        Log.d("Hello","onclick resend email 1") ;
        if (v.getId() == resend_mail) {
            Log.d("Hello","onclick resend email 2") ;
            sendEmailVerification();
        }
    }
}




