package com.example.rahulverma.mobilebasedattendencesystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Afterseeattendance extends AppCompatActivity {
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner sp;
    String item;
    Button seeattendance;
    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterseeattendance);
        sp = (Spinner) findViewById(R.id.spinner);
seeattendance=(Button)findViewById(R.id.seeattendance);
        Intent ipoi = getIntent();
        t=ipoi.getStringExtra("student name");
        Log.v("name  of student",t);
        BackTask bt = new BackTask();
        bt.execute(t);




        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout_seeattendance, R.id.txt, listItems);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
                Log.v("Selected",item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //

seeattendance.setOnClickListener(new View.OnClickListener()

                                 {


                                     @Override
                                     public void onClick(View v) {

                                         Backgroundworker_seeattendance bs=new Backgroundworker_seeattendance(Afterseeattendance.this);
                                         bs.execute(item,t);

                                     }
                                 }
);


    }



    class BackTask extends AsyncTask<String, String, String> {
        ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }


        @Override
        protected String doInBackground(String... params) {
            String type = "login";
            String login_url = "http://palaeozoological-ri.000webhostapp.com/coursename_of_student.php";
            if (type.equals("login")) {
                try {
                    String Studentname = params[0];
                    //String coursename = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("Studentname","UTF-8")+"="+URLEncoder.encode(Studentname,"UTF-8");
                    Log.v("post data",post_data);
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            {
                Log.v("result", result);
                try {
                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        // add interviewee name to arraylist
                        list.add(jsonObject.getString("course1"));
                        list.add(jsonObject.getString("course2"));
                        list.add(jsonObject.getString("course3"));
                        list.add(jsonObject.getString("course4"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listItems.addAll(list);
                //text = sp.getSelectedItem().toString();
                //Log.v("Selected is", text);
                adapter.notifyDataSetChanged();
            }

        }
    }
}







