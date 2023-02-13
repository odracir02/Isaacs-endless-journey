package com.example.isaacsendlessjourney.userdata;

public class UserDataHandler {
    private static UserDataHandler instance;
    private String username;
    private String password;
    private int coins;
    private int clickValue;
    private int multiplier;

    public void create(String username, String password, String coins, String clickValue, String multiplier) {
        this.username = username;
        this.password = password;
        this.coins = Integer.parseInt(coins);
        this.clickValue = Integer.parseInt(clickValue);
        this.multiplier = Integer.parseInt(multiplier);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getClickValue() {
        return clickValue;
    }

    public void setClickValue(int clickValue) {
        this.clickValue = clickValue;
    }

    public int getMultiplier() {
        return multiplier;
    }


    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public static UserDataHandler getInstance() {
        if(instance == null) {
            instance = new UserDataHandler();
        }

        return instance;
    }
}
