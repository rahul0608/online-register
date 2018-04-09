package com.example.rahulverma.mobilebasedattendencesystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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


/*
 * Created by Rahulverma on 03/08/17.
 */

public class Backgroundworker_forgot_password  extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog loading = null;
    Backgroundworker_forgot_password (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://palaeozoological-ri.000webhostapp.com/Get_email_and_password.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
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
        alertDialog.setTitle("Password recovery");
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage("Checking username!\n Please Wait!!");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
        loading.setCancelable(false);

    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();
        String substr = ",";
        String[] parts = result.split(substr);
        String email = parts[0];
        String password = parts[1];
        String subject="Password Recovery";
        Log.v("password b",password);
        Log.v("email b",email);
        String message="Hi,Forgot your passowrd?\n Here is your password: - ( "+password+")\nThank you\n Team (Online Register) ";

        if(result.contains("username does not exists"))  /*in my getemail.php i have set the message to username does not exist  if username is not in the databse*/
        {
            alertDialog.setMessage(result);//the message in alert dialog is from login_student
            alertDialog.show();

        }
        else
        {            SendMail sm = new SendMail(context,email, subject, message);

            //Executing sendmail to send email
            sm.execute();



        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}



