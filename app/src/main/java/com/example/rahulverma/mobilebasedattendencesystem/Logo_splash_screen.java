package com.example.rahulverma.mobilebasedattendencesystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class Logo_splash_screen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Logo_splash_screen.this,MainActivity.class);
                Logo_splash_screen.this.startActivity(mainIntent);
                Logo_splash_screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }

