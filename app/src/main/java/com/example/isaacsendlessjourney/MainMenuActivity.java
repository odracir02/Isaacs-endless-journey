package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void start(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void unlocks(View view) {
        Intent intent = new Intent(getApplicationContext(),UnlocksActivity.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
    }
}