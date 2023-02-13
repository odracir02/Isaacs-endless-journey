package com.example.isaacsendlessjourney.items;

import com.example.isaacsendlessjourney.db.DatabaseHandler;

import java.util.Map;

public class ItemsHandler {
    private static ItemsHandler instance;
    private Map<String, Object> itemsDB;

    public void updateLocalItems(Map<String, Object> itemsDB) {
        this.itemsDB = itemsDB;
    }

    public void getAllItems() {
        if(itemsDB == null) {
            DatabaseHandler.getInstance().getAllItems();
        }

        System.out.println(this.itemsDB);
    }

    public static ItemsHandler getInstance() {
        if(instance == null) {
            instance = new ItemsHandler();
        }

        return instance;
    }
}
