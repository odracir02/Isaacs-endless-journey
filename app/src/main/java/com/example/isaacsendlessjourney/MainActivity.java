package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int coins = 0;
    private int clickValue = 3;
    private int clickMultiplier = 1;

    private TextView tvCoins;
    private Button btnClicker;

    private Button btnHome;
    private Button btnShop;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Prevent dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCoins = findViewById(R.id.tvCoins);
        btnClicker = findViewById(R.id.btnClicker);

        btnHome = findViewById(R.id.btnHome);
        btnShop = findViewById(R.id.btnShop);
        btnSettings = findViewById(R.id.btnSettings);
    }

    /*
    * CLICKER
    */
    private String parseCoins(int coins) {
        String strCoin = String.valueOf(coins);
        System.out.println(strCoin);
        String finalStr;

        // Million
        if(strCoin.length() > 6) {
            finalStr = strCoin.substring(0,(strCoin.length() - 3) - 3) + "." + strCoin.substring((strCoin.length() - 3) - 2,(strCoin.length() - 3) - 1) + "M";

            return finalStr;
        }

        // Thousand
        if(strCoin.length() > 3) {
            finalStr = strCoin.substring(0,(strCoin.length() - 1) - 2) + "." + strCoin.substring((strCoin.length() - 1) - 2,(strCoin.length() - 1) - 1) + "K";

            return finalStr;
        }

        // Under 10
        if(strCoin.length() < 2) {
            return "0" + coins;
        }

        return String.valueOf(coins);
    }

    public void click(View view) {
        coins += clickValue * clickMultiplier;
        tvCoins.setText(parseCoins(coins));
    }

    /*
    * MENU
    */
    public void intentMain(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void intentShop(View view) {
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        startActivity(intent);
    }

    public void intentSettings(View view) {
        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
    }
}