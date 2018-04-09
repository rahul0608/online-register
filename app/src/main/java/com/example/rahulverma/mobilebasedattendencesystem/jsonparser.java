package com.example.rahulverma.mobilebasedattendencesystem;

/**
 * Created by Rahulverma on 09/09/17.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Rahulverma on 29/08/17.
 */

public class jsonparser  {

    static InputStream is ;
    static JSONArray jObj;
    static String json ;

    // constructor
    public jsonparser() {

    }

    public JSONArray getJSONFromUrl(String url) {

        // Making HTTP request
        try {


            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            Log.i("test", "hello1");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.i("test", "hello");
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.i("test", "4");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            Log.i("test", "3");
            StringBuilder sb = new StringBuilder();
            Log.i("test", "2");
            String line = null;
            Log.i("test", "1");
            while ((line = reader.readLine()) != null) {
                Log.i("test", "line");
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        Log.i("test", "afterline");
        // try parse the string to a JSON object
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.i("parsing..", ""+jsonObject.get("list").toString());
            jObj = new JSONArray(jsonObject.get("list").toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;


    }
}


