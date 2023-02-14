package com.example.isaacsendlessjourney.items;

import com.example.isaacsendlessjourney.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemsHandler {
    private static ItemsHandler instance;
    // private Map<String, Object> itemsDB;
    private List<Map<String, String>> itemsDB = new ArrayList<Map<String, String>>();

    // TODO: This method is hardcoded for now (dev purposes) until db is fixed
    public void updateLocalItems() {
        // Devil pool
        Map<String, String> item = new HashMap<>();
        item.put("name", "Betrayal");
        item.put("description", "Turn your enemy");
        item.put("pool", "devil");
        item.put("quality", "1");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Brimstone");
        item.put("description", "Blood laser barrage");
        item.put("pool", "devil");
        item.put("quality", "4");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Pentagram");
        item.put("description", "DMG up");
        item.put("pool", "devil");
        item.put("quality", "3");
        this.itemsDB.add(item);

        // Angel pool
        item = new HashMap<>();
        item.put("name", "Hallowed Ground");
        item.put("description", "Portable sanctuary");
        item.put("pool", "angel");
        item.put("quality", "1");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Holy Mantle");
        item.put("description", "Holy shield");
        item.put("pool", "angel");
        item.put("quality", "4");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Inmaculate Conception");
        item.put("description", "Feel them love");
        item.put("pool", "angel");
        item.put("quality", "1");
        this.itemsDB.add(item);

        // Item pool
        item = new HashMap<>();
        item.put("name", "Big Fan");
        item.put("description", "Fat protector");
        item.put("pool", "item");
        item.put("quality", "1");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Flamboyant protector");
        item.put("description", "Psy Fly");
        item.put("pool", "item");
        item.put("quality", "4");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Dark Bum");
        item.put("description", "He wants to take your life");
        item.put("pool", "item");
        item.put("quality", "3");
        this.itemsDB.add(item);

        // Boss pool
        item = new HashMap<>();
        item.put("name", "Magic Mushroom");
        item.put("description", "All stats up!");
        item.put("pool", "boss");
        item.put("quality", "4");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Match Book");
        item.put("description", "Evil up");
        item.put("pool", "boss");
        item.put("quality", "1");
        this.itemsDB.add(item);

        item = new HashMap<>();
        item.put("name", "Stapler");
        item.put("description", "DMG up");
        item.put("pool", "boss");
        item.put("quality", "3");
        this.itemsDB.add(item);

    }

   /* public void getAllItems() {
        if(itemsDB == null) {
            DatabaseHandler.getInstance().getAllItems();
        }

        System.out.println(this.itemsDB);
    }*/

    public Map<String, String> getItemFromPool(String pool) {
        List<Map<String, String>> filteredItems = new ArrayList<Map<String, String>>();

        for(Map<String, String> item : this.itemsDB) {
            if(item.get("pool").equals(pool)) {
                filteredItems.add(item);
            }
        }

        Random rand = new Random();
        return filteredItems.get(rand.nextInt(filteredItems.size()));
    }

    public static ItemsHandler getInstance() {
        if(instance == null) {
            instance = new ItemsHandler();
        }

        return instance;
    }
}
