package com.example.rahulverma.mobilebasedattendencesystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class afterstudentlogin extends AppCompatActivity {
    private Button logout;
    private ImageButton seeattendence,additionalmaterial,seeattendance;
    String txtData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterstudentlogin);
        Intent v = getIntent();
//The second parameter below is the default string returned if the value is not there.
         txtData = v.getExtras().getString("txtData","");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(afterstudentlogin.this);

// set title
        alertDialogBuilder.setTitle("Log in Success");

// set dialog message
        alertDialogBuilder.setMessage(txtData).setCancelable(true);

// create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

// show it
        alertDialog.show();
// After some action



        logout=(Button)findViewById(R.id.button2);
        seeattendence=(ImageButton)findViewById(R.id.imageButton4);
        additionalmaterial=(ImageButton)findViewById(R.id.imageButton5);
        additionalmaterial.setOnClickListener(new View.OnClickListener()
                                              {


                                                  @Override
                                                  public void onClick(View v) {
                                                      Intent mnb=new Intent(afterstudentlogin.this,seeadditionalmaterialimage.class);
                                                      startActivity(mnb);

                                                  }
                                              }
        );


        seeattendence.setOnClickListener(new  View.OnClickListener()
        {



            @Override
            public void onClick(View v) {

                Intent ipoi= new Intent(afterstudentlogin.this,Afterseeattendance.class);
                ipoi.putExtra("student name",txtData);
                startActivity(ipoi);

            }
        });



        logout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(afterstudentlogin.this);
                alertDialogBuilder.setTitle("Log Out?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                Intent f=new Intent(afterstudentlogin.this,MainActivity.class);
                                startActivity(f);


                            }

                        });
                alertDialogBuilder.setNegativeButton( "No",
                        new  DialogInterface.OnClickListener()

                        {

                            public void onClick(DialogInterface dialog, int id)
                            {

                                dialog.cancel();


                            }
                        }
                );


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }

        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(afterstudentlogin.this);
        alertDialogBuilder.setTitle("Log Out?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        Intent f=new Intent(afterstudentlogin.this,MainActivity.class);
                        startActivity(f);


                    }

                });
        alertDialogBuilder.setNegativeButton( "No",
                new  DialogInterface.OnClickListener()

                {

                    public void onClick(DialogInterface dialog, int id)
                    {

                        dialog.cancel();


                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
