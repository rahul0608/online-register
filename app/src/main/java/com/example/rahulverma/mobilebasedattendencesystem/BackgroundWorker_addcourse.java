package com.example.rahulverma.mobilebasedattendencesystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by Rahulverma on 07/08/17.
 */



public class BackgroundWorker_addcourse extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog loading = null;

    BackgroundWorker_addcourse (Context ctx) {
        context = ctx;

    }
    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String login_url = "http://palaeozoological-ri.000webhostapp.com/new_course/creatingnewcoursetable.php";
        if(type.equals("login")) {
            try {
                String coursecode = params[1];
                String coursename = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("coursecode","UTF-8")+"="+URLEncoder.encode(coursecode,"UTF-8")+"&"
                        +URLEncoder.encode("coursename","UTF-8")+"="+URLEncoder.encode(coursename,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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
    protected void onPreExecute() {
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage("Adding course!");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setCancelable(false);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Course not added");
        loading.show();



    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();

        if(result.contains("created successfully"))  /*in my createnewcoursetable.php i have set the message to login success if username and password is correct*/
        {

            Toast.makeText(context,"Course added successfully",Toast.LENGTH_SHORT).show();

        }
        else
        {

            alertDialog.setMessage(result);//the message in the alert dialog is from the login_teacher.php
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}