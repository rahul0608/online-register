package com.example.rahulverma.mobilebasedattendencesystem;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class markattendance extends AppCompatActivity {
    List<studentdetails> yourData = new ArrayList<studentdetails>();
    StringBuilder checkedlist = new StringBuilder();
    JSONArray j=new JSONArray();
    private static String url="https://palaeozoological-ri.000webhostapp.com/get_name_of_student_in_json.php";
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markattendance);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        jsonparser jParser = new jsonparser();
       Intent ijmkk=getIntent();
       final String namee= ijmkk.getStringExtra("name of the teacher");
        Log.v("name in maek",namee);

        //String teachername=ijmkk.getStringExtra("name of the teacher");


        //Getting JSON string from URL ------ Used JSON Array from Android
        JSONArray json = jParser.getJSONFromUrl(url);
        Log.v("sd", json.toString());

        b = (Button) findViewById(R.id.button1);

        try {
            for (int i = 0; i < json.length(); i++) {

                JSONObject c = json.getJSONObject(i);// Used JSON Object from Android

                String name = c.getString("Student_name");
                String enroll = c.getString("Student_id");
                Boolean j = false;


                yourData.add(new studentdetails(enroll, name, j));

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final ListView yourListView = (ListView) findViewById(R.id.listViewID);

        final ListAdapter customAdapter = new ListAdapter(this, R.layout.studentlistrow, yourData, checkedlist, j);

        yourListView.setAdapter(customAdapter);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        /*Intent i=new Intent(MainActivity.this,Newclass.class);
        i.putExtra("studentlist",checkedlist.toString());
        startActivity(i);*/


                Log.v("json in buton is",j.toString());
                Intent sel=new Intent(markattendance.this,aftermarkattendence.class);
                sel.putExtra("json is",j.toString());
                sel.putExtra("name is",namee);
                startActivity(sel);

            }
        });


    }
}

