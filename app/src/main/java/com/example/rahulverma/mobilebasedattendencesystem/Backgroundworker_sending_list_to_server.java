package com.example.rahulverma.mobilebasedattendencesystem;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
 * Created by Rahulverma on 08/09/17.
 */

public class Backgroundworker_sending_list_to_server extends AsyncTask<String,String,String>

{
    Context context;
    AlertDialog alertDialog;
    ProgressDialog loading = null;

    Backgroundworker_sending_list_to_server (Context ctx) {
        context = ctx;

    }
    @Override
    protected String doInBackground(String... params) {

        String type = "login";
        String login_url = "https://palaeozoological-ri.000webhostapp.com/putting_marked_student_in%20_database.php";
        if(type.equals("login")) {
            try {

                String jsonarray = params[0];
                String coursename=params[1];
                Log.v("jarray in bac",jsonarray);
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("jsonarray","UTF-8")+"="+URLEncoder.encode(jsonarray,"UTF-8")+"&"
                        +URLEncoder.encode("coursename","UTF-8")+"="+URLEncoder.encode(coursename,"UTF-8");
                Log.v("Postdatainserver",post_data);
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
        loading.setMessage("Uploading!!");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setCancelable(false);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Can not marked");
        loading.show();



    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();
        Log.v("servermessage",result.toString());

        if(result.contains("successfully"))  /*in my putting_marked_student_indatabse.php i have set the message to sucessfullly if attendance is marked*/
        {

            Toast.makeText(context,"uploaded successfully",Toast.LENGTH_SHORT).show();

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
