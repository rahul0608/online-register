package com.example.rahulverma.mobilebasedattendencesystem;

/**
 * Created by Rahulverma on 10/09/17.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by Rahulverma on 03/08/17.
 */
public class Backgroundworker_seeattendance extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog loading = null;
    Backgroundworker_seeattendance (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = "login";
        String login_url = "http://palaeozoological-ri.000webhostapp.com/checking_attendance.php";
        if(type.equals("login")) {
            try {
                String studentname = params[1];
                String coursename = params[0];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("studentname","UTF-8")+"="+URLEncoder.encode(studentname,"UTF-8")+"&"
                        +URLEncoder.encode("coursename","UTF-8")+"="+URLEncoder.encode(coursename,"UTF-8");
                Log.v("Data in bs",post_data);
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
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Attendance");
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage("checking Attendance");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
        loading.setCancelable(false);

    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();

            alertDialog.setMessage(result);//the message in alert dialog is from login_student
            alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
