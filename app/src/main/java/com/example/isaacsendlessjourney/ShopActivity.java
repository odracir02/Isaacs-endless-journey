package com.example.isaacsendlessjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.isaacsendlessjourney.db.DatabaseHandler;
import com.example.isaacsendlessjourney.items.ItemsHandler;
import com.example.isaacsendlessjourney.userdata.UserDataHandler;
import com.google.firebase.firestore.auth.User;

import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private int baseAngelPrice = 300;
    private int baseDevilPrice = 250;
    private int baseItemPrice = 200;
    private int baseShopPrice = 100;


    private Button btnAngel;
    private Button btnDevil;
    private Button btnItem;
    private Button btnShop;

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

        btnAngel = findViewById(R.id.btnAngel);
        btnDevil = findViewById(R.id.btnDevil);
        btnItem = findViewById(R.id.btnItem);
        btnShop = findViewById(R.id.btnShop);

        btnAngel.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseAngelPrice) + "¢");
        btnDevil.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseDevilPrice) + "¢");
        btnItem.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseItemPrice) + "¢");
        btnShop.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseShopPrice) + "¢");
    }

    public void getAngelItem(View view) {
        buyItem("angel", baseAngelPrice);
        btnAngel.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseAngelPrice) + "¢");
    }
    public void getDevilItem(View view) {
        buyItem("devil", baseDevilPrice);
        btnDevil.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseDevilPrice) + "¢");
    }

    public void getItemItem(View view) {
        buyItem("item", baseItemPrice);
        btnItem.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseItemPrice) + "¢");
    }

    public void getShopItem(View view) {
        buyItem("boss", baseShopPrice);
        btnShop.setText(Integer.toString(UserDataHandler.getInstance().getNumberOfBuys() * baseShopPrice) + "¢");
    }

    private void buyItem(String pool, int casePrice) {
        System.out.println("coins:" + UserDataHandler.getInstance().getCoins());
        System.out.println("price:" + casePrice * UserDataHandler.getInstance().getNumberOfBuys());

        if(UserDataHandler.getInstance().getCoins() >= casePrice * UserDataHandler.getInstance().getNumberOfBuys()) {
            Map<String, String> item = ItemsHandler.getInstance().getItemFromPool(pool);
            Toast.makeText(getApplicationContext(), item.get("name"), Toast.LENGTH_LONG).show();
            UserDataHandler.getInstance().setClickValue(
                    UserDataHandler.getInstance().getClickValue() + Integer.parseInt(item.get("quality"))
            );
            UserDataHandler.getInstance().setCoins(
                    UserDataHandler.getInstance().getCoins() - (casePrice * UserDataHandler.getInstance().getNumberOfBuys())
            );
            UserDataHandler.getInstance().setNumberOfBuys(
                    UserDataHandler.getInstance().getClickValue() + 1
            );
        } else {
            Toast.makeText(getApplicationContext(), "No tienes dinero :(", Toast.LENGTH_LONG).show();
        }
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