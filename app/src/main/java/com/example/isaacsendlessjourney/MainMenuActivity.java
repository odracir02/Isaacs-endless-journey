package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainMenuActivity extends AppCompatActivity {
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hide navbar
        hideNavbar();

        // Prevent dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main_menu);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // main menu music :)
        player = MediaPlayer.create(this, R.raw.main_menu);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }

    public void intentStart(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        player.pause();
    }

    public void intentUnlocks(View view) {
        Intent intent = new Intent(getApplicationContext(),UnlocksActivity.class);
        startActivity(intent);
        player.pause();
    }

    public void intentSettings(View view) {
        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
        player.pause();
    }

    /*
     * UTILS
     */
    public void hideNavbar() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}