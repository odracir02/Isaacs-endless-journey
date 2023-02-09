package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIEMPO = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Prevent dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_splash_view);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(intent);
            }
        }, SPLASH_TIEMPO);
    }
}