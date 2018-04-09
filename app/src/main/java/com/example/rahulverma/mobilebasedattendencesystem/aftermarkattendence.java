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

public class aftermarkattendence extends AppCompatActivity {
    Button b;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner sp;
    String textt=null;
    String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftermarkattendence);
        //Intent nmji=getIntent();
        //tring njhi=nmji.getStringExtra("Teachername is");
        //Log.v("wtf",njhi);

        Intent i = getIntent();
String t=i.getStringExtra("name is");
        Log.v("name in aftermark",t);
        final String str = i.getStringExtra("json is");
        Log.v("jh", str);
        BackTask bt = new BackTask();
        bt.execute(t);


        sp = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listItems);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 item = parent.getItemAtPosition(position).toString();
                Log.v("Selected",item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // textt = sp.getSelectedItem().toString();
        //Log.v("selected spinner",textt);



        b = (Button) findViewById(R.id.buttongh);
        b.setOnClickListener(new View.OnClickListener() {


                                 @Override
                                 public void onClick(View v) {
                                     Backgroundworker_sending_list_to_server bc = new Backgroundworker_sending_list_to_server(aftermarkattendence.this);
                                     bc.execute(str,item);

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
            String login_url = "http://palaeozoological-ri.000webhostapp.com/coursename.php";
            if (type.equals("login")) {
                try {
                    String Teachername = params[0];
                    //String coursename = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("Teachername","UTF-8")+"="+URLEncoder.encode(Teachername,"UTF-8");
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
                        Log.v("list of courses",list.toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("in catch","block");
                }

                listItems.addAll(list);
              // String  text = sp.getSelectedItem().toString();
             //   Log.v("Selected is", text);
                adapter.notifyDataSetChanged();
            }

        }
    }
}



