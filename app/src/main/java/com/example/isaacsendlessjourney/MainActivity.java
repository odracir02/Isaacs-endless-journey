package com.example.isaacsendlessjourney;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int coins = 0;
    private int clickValue = 43531245;
    private int clickMultiplier = 1;

    private TextView tvCoins;
    private Button btnClicker;

    private Button btnHome;
    private Button btnShop;
    private Button btnSettings;

    // Firebase
    FirebaseFirestore db;



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

        db = FirebaseFirestore.getInstance();
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
        /*Intent intent = new Intent(getApplicationContext(),ShopActivity.class);
        startActivity(intent);*/
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("coins", 15);
        saveData.put("coins_click", 50);
        saveData.put("multiplier", 20);

        this.db.collection("user_data")
                .document("test")
                .update(saveData);

    }

    public void intentSettings(View view) {
        /*Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);*/



        // Create a new user with a first and last name
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("coins", this.coins);
        saveData.put("coins_click", this.clickValue);
        saveData.put("multiplier", this.clickMultiplier);

        this.db.collection("user_data")
                .document("test")
                .set(saveData);

        // Add a new document with a generated ID
        /*this.db.collection("user_data")
                .add(saveData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("subido correctamente");
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("la cague :(");
                        Log.w(TAG, "Error adding document", e);
                    }
                });*/
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