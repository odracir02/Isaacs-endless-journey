package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.isaacsendlessjourney.db.DatabaseHandler;
import com.example.isaacsendlessjourney.items.ItemsHandler;

import java.util.Map;

public class ShopActivity extends AppCompatActivity {

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
        this.setContentView(R.layout.activity_shop);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    public void getAngelItem(View view) {
        Map<String, String> item = ItemsHandler.getInstance().getItemFromPool("angel");
    }
    public void getDevilItem(View view) {
        Map<String, String> item = ItemsHandler.getInstance().getItemFromPool("devil");
    }

    public void getItemItem(View view) {
        Map<String, String> item = ItemsHandler.getInstance().getItemFromPool("item");
    }

    public void getShopItem(View view) {
        Map<String, String> item = ItemsHandler.getInstance().getItemFromPool("boss");
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