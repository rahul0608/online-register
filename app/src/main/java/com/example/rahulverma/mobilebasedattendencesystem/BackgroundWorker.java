package com.example.rahulverma.mobilebasedattendencesystem;

/**
 * Created by Rahulverma on 05/08/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.AlertDialog;
        import android.content.Context;
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

/**
 * Created by Rahulverma on 03/08/17.
 */
public class BackgroundWorker extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog loading = null;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://palaeozoological-ri.000webhostapp.com/student_login.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        alertDialog.setTitle("Login Failed");
        loading = new ProgressDialog(context);
        loading.setCancelable(true);
        loading.setMessage("Loging In!");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
        loading.setCancelable(false);

    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();


        if(result.contains("Check username or password"))  /*in my login.php i have set the message to login success if username and password is correct*/
        {
            alertDialog.setMessage(result);//the message in alert dialog is from login_student
            alertDialog.show();

        }
        else
            {
                Intent v= new Intent(context,afterstudentlogin.class);
                String txtData = result.toString();
                v.putExtra("txtData", txtData);
                context.startActivity(v);

        }
        }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}



