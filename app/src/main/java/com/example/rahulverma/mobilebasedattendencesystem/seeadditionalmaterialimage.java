package com.example.rahulverma.mobilebasedattendencesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.R.attr.content;
import static android.R.attr.id;

public class seeadditionalmaterialimage extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeadditionalmaterialimage);
        web=(WebView)findViewById(R.id.webview1);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("http://palaeozoological-ri.000webhostapp.com/PhotoUpload/uploads/0.png");

    }
}
