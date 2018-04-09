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
//created by Rahul verma
//I am creating a new table for each course added and the table will contain student id and the student attandance and student name
public class addcourse extends AppCompatActivity {
    Button add;
    EditText coursename, coursecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        add = (Button) findViewById(R.id.addbutton);
        coursecode = (EditText) findViewById(R.id.editText7);
        coursename = (EditText) findViewById(R.id.editText8);

    }

    public void OnLogin(View view)

    {
        String coursecodee = coursecode.getText().toString();
        String coursenamee = coursename.getText().toString();
        String type = "login";
        if (coursecodee.startsWith("c")) {

            if (isOnline() == false) {
                Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            } else {
                if (coursecodee.isEmpty() || coursenamee.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter both coursename and coursetitle", Toast.LENGTH_SHORT).show();
                } else {

                    BackgroundWorker_addcourse backgroundWorker = new BackgroundWorker_addcourse(this);
                    backgroundWorker.execute(type, coursecodee, coursenamee);


                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "The couse name should start with c", Toast.LENGTH_LONG).show();


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