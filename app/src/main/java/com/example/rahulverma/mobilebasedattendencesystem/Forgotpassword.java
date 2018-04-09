package com.example.rahulverma.mobilebasedattendencesystem;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgotpassword extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText usernameedittext;
    // private EditText editTextSubject;
    //  private EditText editTextMessage;
    //Send button
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        //Initializing the views
        usernameedittext = (EditText) findViewById(R.id.editText5);
        //editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        //editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        String username = usernameedittext.toString();
        buttonSend = (Button) findViewById(R.id.Sendpassword);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        Backgroundworker_forgot_password ba = new Backgroundworker_forgot_password(this);
        ba.execute("login", usernameedittext.getText().toString());
        //Creating SendMail object

    }

    @Override
    public void onClick(View v) {
        if(isOnline()==true) {
            sendEmail();
        }
        else
        {
            Toast.makeText(Forgotpassword.this, "No Internet Connection!!", Toast.LENGTH_SHORT).show();

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