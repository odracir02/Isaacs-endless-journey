package com.example.isaacsendlessjourney;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.isaacsendlessjourney.db.DatabaseHandler;
import com.example.isaacsendlessjourney.items.ItemsHandler;
import com.example.isaacsendlessjourney.userdata.UserDataHandler;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int coins;
    private int clickValue;
    private int clickMultiplier;

    private TextView tvCoins;
    private Button btnClicker;

    private Button btnHome;
    private Button btnShop;
    private Button btnSettings;

    // Firebase
    private DatabaseHandler db;
    private UserDataHandler userDataHandler;

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
        this.setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCoins = findViewById(R.id.tvCoins);
        btnClicker = findViewById(R.id.btnClicker);

        btnHome = findViewById(R.id.btnHome);
        btnShop = findViewById(R.id.btnShop);
        btnSettings = findViewById(R.id.btnSettings);

        // Firebase
        db = DatabaseHandler.getInstance();

        // main menu music :)
        player = MediaPlayer.create(this, R.raw.cathedral);
        player.setLooping(true);
        player.setVolume(25, 25);
        player.start();

        // get user data
        userDataHandler = UserDataHandler.getInstance();

        this.coins = userDataHandler.getCoins();
        this.clickValue = userDataHandler.getClickValue();
        this.clickMultiplier = userDataHandler.getMultiplier();

        tvCoins.setText(String.valueOf(this.coins));

        // load items
        ItemsHandler.getInstance().updateLocalItems();
    }

    /*
    * CLICKER
    */
    private String parseCoins(int coins) {
        String strCoin = String.valueOf(coins);
        String finalStr;

        // Million
        if(strCoin.length() > 6) {
            finalStr = strCoin.substring(0,(strCoin.length() - 1) - 5) + "." + strCoin.substring((strCoin.length() - 1) - 5,(strCoin.length() - 1) - 3) + "M";

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
        System.out.println(this.getClass().getSimpleName());
        if(!this.getClass().getSimpleName().equals("MainActivity")) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    public void intentShop(View view) {
        Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        startActivity(intent);
    }

    public void intentSettings(View view) {
        Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        Bundle b = new Bundle();
        b.putInt("coins", this.coins);
        b.putInt("clickValue", this.clickValue);
        b.putInt("clickMultiplier", this.clickMultiplier);
        intent.putExtras(b);
        startActivity(intent);

        /* Create a new user with a first and last name
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("coins", this.coins);
        saveData.put("coins_click", this.clickValue);
        saveData.put("multiplier", this.clickMultiplier);

        this.db.saveUserData("test", saveData);

        this.db.collection("user_data")
                .document("test")
                .set(saveData);*/
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