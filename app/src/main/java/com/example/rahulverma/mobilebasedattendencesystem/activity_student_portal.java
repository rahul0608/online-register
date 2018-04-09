package com.example.rahulverma.mobilebasedattendencesystem;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import static com.example.rahulverma.mobilebasedattendencesystem.R.layout.activity_student_portal;


public class activity_student_portal extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    Button loginButton,forgotpassword;
    ProgressBar newprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_student_portal);
        UsernameEt = (EditText) findViewById(R.id.editText);
        PasswordEt = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.btnLogin);
        forgotpassword=(Button)findViewById(R.id.forgotpasswordbutton);
        forgotpassword.setOnClickListener(new View.OnClickListener()
                                          {

                                              @Override
                                              public void onClick(View v) {
                                                  Intent  forgot=new Intent(activity_student_portal.this,Forgotpassword.class);
                                                  startActivity(forgot);

                                              }
                                          }
        );

    }


    public void OnLogin(View view) {


        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        if (isOnline() == false) {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();

        } else {
            if (username.isEmpty() || password.isEmpty())

            {
                Toast.makeText(getApplicationContext(), "Enter both Username and Password", Toast.LENGTH_SHORT).show();
            } else {
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, username, password);
            }
        }
    }


    public boolean isOnline() {
            ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {

                return false;
            }
            return true;
    }
}