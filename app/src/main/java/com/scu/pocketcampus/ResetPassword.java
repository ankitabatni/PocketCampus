package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }
    public void onButtonClick(View v) {

        if (v.getId() == R.id.login_after_password_reset) {
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
    }
}
