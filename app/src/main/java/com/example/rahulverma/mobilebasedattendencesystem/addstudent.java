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

public class addstudent extends AppCompatActivity {
    EditText nameofstudent,idofstudent,passwordofstudent,usernameofstudent,addemail;
    Button addstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        nameofstudent=(EditText)findViewById(R.id.nameofstudent);
        idofstudent=(EditText)findViewById(R.id.idofthestudent);
        passwordofstudent=(EditText)findViewById(R.id.passwordforstudent);
        usernameofstudent=(EditText)findViewById(R.id.studentusername);
        addstudent=(Button)findViewById(R.id.addstudent);
        addemail=(EditText)findViewById(R.id.studentemail);
        addstudent.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {


                    String studentname = nameofstudent.getText().toString();
                    String studentusername = usernameofstudent.getText().toString();
                String studentid=idofstudent.getText().toString();
                String studentpassword=passwordofstudent.getText().toString();
                String email=addemail.getText().toString();
                    String type = "login";


                        if (isOnline() == false) {
                            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
                        } else {
                            if (studentid.isEmpty() || studentname.isEmpty() || studentusername.isEmpty() || studentpassword.isEmpty()||email.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                            } else {

                                BackgroundWorker_addstudent backgroundWorker = new BackgroundWorker_addstudent(addstudent.this);
                                backgroundWorker.execute(type, studentname, studentusername,studentid,studentpassword,email);


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
        });

            }
        }
