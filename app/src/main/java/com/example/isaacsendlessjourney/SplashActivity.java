package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIEMPO = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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