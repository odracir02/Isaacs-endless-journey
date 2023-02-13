package com.example.isaacsendlessjourney.userdata;

public class UserDataHandler {
    private static UserDataHandler instance;
    private String username;
    private int coins;
    private int clickValue;
    private int multiplier;

    public void create(String username, String coins, String clickValue, String multiplier) {
        this.username = username;
        this.coins = Integer.parseInt(coins);
        this.clickValue = Integer.parseInt(clickValue);
        this.multiplier = Integer.parseInt(multiplier);
    }

    public String getUsername() {
        return username;
    }

    public int getCoins() {
        return coins;
    }

    public int getClickValue() {
        return clickValue;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static UserDataHandler getInstance() {
        if(instance == null) {
            instance = new UserDataHandler();
        }

        return instance;
    }
}
