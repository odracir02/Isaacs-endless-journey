package com.example.isaacsendlessjourney.db;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DatabaseHandler {
    private static DatabaseHandler instance;

    private FirebaseFirestore db;

    public DatabaseHandler() {
        this.db = FirebaseFirestore.getInstance();
    }

    public boolean loginUser(String user, String password) {
        return true;
    }

    public void saveUserData(String document, Map<String, Object> data) {
        this.db.collection("user_data")
                .document(document)
                .set(data);
    }

    public static DatabaseHandler getInstance() {
        if(instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }
}
