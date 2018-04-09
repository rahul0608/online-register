package com.example.rahulverma.mobilebasedattendencesystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static com.example.rahulverma.mobilebasedattendencesystem.R.id.linearLayout;
import static com.example.rahulverma.mobilebasedattendencesystem.R.layout.activity_afterteacherlogin;
import static com.example.rahulverma.mobilebasedattendencesystem.R.layout.activity_teacher_login;

public class teacher_login extends AppCompatActivity {
    Button login;

    EditText usernamee, passworde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_teacher_login);
        login = (Button) findViewById(R.id.teacherloginButton);
        usernamee = (EditText) findViewById(R.id.editText3);
        passworde = (EditText) findViewById(R.id.editText4);


    }


    public void OnLogin(View view) {

        String username = usernamee.getText().toString();
        String password = passworde.getText().toString();
        String type = "login";
        if (isOnline() == false) {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();

        }
        else {
            if (username.isEmpty() || password.isEmpty())

            {
                Toast.makeText(getApplicationContext(), "Enter both Username and Password", Toast.LENGTH_SHORT).show();
            } else {
                BackgroundWorker_teacher backgroundWorker = new BackgroundWorker_teacher(this);
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

