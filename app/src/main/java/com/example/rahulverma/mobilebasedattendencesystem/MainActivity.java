package com.example.rahulverma.mobilebasedattendencesystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.rahulverma.mobilebasedattendencesystem.R.layout.activity_student_portal;

public class MainActivity extends AppCompatActivity {
    ImageButton teacherlogin,studentlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        teacherlogin=(ImageButton) findViewById(R.id.teacherbutton);
        studentlogin=(ImageButton) findViewById(R.id.studentbutton);
        studentlogin.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(isOnline()==false)
                {
                    Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();

                }
                else{
                    Intent i = new Intent(MainActivity.this, activity_student_portal.class);
                    startActivity(i);
                }
            }
        });

   teacherlogin.setOnClickListener(new View.OnClickListener()

                                   {

                                       @Override
                                       public void onClick(View v) {
                                           if(isOnline()==false)
                                           {
                                               Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
                                           }
                                           else{
                                           Intent n= new Intent(MainActivity.this,teacher_login.class);
                                           startActivity(n);
                                           }
                                       }
                                   }
   );

    }
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){

            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit?");
        alertDialogBuilder
                .setMessage("Press yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
