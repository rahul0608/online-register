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
import android.widget.Toast;

public class Afterteacherlogin extends AppCompatActivity {
    ImageButton addcourse,add,addstudentbutton,attendence;
    Button logout;
    AlertDialog a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterteacherlogin);
        attendence=(ImageButton)findViewById(R.id.imageButton3);
        Intent v = getIntent();
//The second parameter below is the default string returned if the value is not there.
        final String txtData = v.getExtras().getString("txtData","");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Afterteacherlogin.this);

// set title
        alertDialogBuilder.setTitle("Log in Success");

// set dialog message
        alertDialogBuilder.setMessage(txtData).setCancelable(true);

// create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        Intent nmji=new Intent(Afterteacherlogin.this,aftermarkattendence.class);
        nmji.putExtra("Teachername",txtData);

// show it
        alertDialog.show();
        addstudentbutton=(ImageButton)findViewById(R.id.imageButton);
        addcourse=(ImageButton)findViewById(R.id.addcourse);
        logout=(Button)findViewById(R.id.logouttbutton);
        add=(ImageButton)findViewById(R.id.imageButton11);
        add.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Intent j=new Intent(Afterteacherlogin.this,addmaterial.class);
                startActivity(j);
            }
        });


        addcourse.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent m=new Intent(Afterteacherlogin.this,addcourse.class);
                startActivity(m);
            }
        });

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Afterteacherlogin.this);
                alertDialogBuilder.setTitle("Log Out?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                Intent f=new Intent(Afterteacherlogin.this,MainActivity.class);
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
        addstudentbutton.setOnClickListener(new View.OnClickListener()
                                            {

                                                @Override
                                                public void onClick(View v) {
                                                    Intent hgfd=new Intent(Afterteacherlogin.this,addstudent.class);
                                                    startActivity(hgfd);

                                                }
                                            }
        );

       attendence.setOnClickListener(new View.OnClickListener()
        {

                                              //@Override
                                              public void onClick(View v) {
                                                  Intent ijmkk= new Intent(Afterteacherlogin.this,markattendance.class);
                                                  ijmkk.putExtra("name of the teacher",txtData);
                                                  startActivity(ijmkk);

                                              }
                                          }
        );
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Afterteacherlogin.this);
        alertDialogBuilder.setTitle("Log Out?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        Intent f=new Intent(Afterteacherlogin.this,MainActivity.class);
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



}
