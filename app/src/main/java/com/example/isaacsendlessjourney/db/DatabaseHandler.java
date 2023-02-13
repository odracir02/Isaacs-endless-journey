package com.example.isaacsendlessjourney.db;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.isaacsendlessjourney.MainMenuActivity;
import com.example.isaacsendlessjourney.userdata.UserDataHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    private static DatabaseHandler instance;

    private FirebaseFirestore db;

    public DatabaseHandler() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void loginUser(String username, String password) {
        DocumentReference docRef = db.collection("user_data")
                .document(username);

        boolean userLogged = false;
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    // If user exists
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();

                        // If password is correct
                        if(data.get("username").equals(username) && data.get("password").equals(password)) {
                            UserDataHandler.getInstance().create(data.get("username").toString(), data.get("password").toString(), data.get("coins").toString(), data.get("coins_click").toString(), data.get("multiplier").toString());
                            System.out.println("User logged!");
                        } else {
                            System.out.println("Wrong password");
                        }
                    } else {
                        // If user not exists, create user
                        System.out.println("Not user found, creating...");
                        createUser(username, password);
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void createUser(String username, String password) {
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("username", username);
        saveData.put("password", password);
        saveData.put("coins", 0);
        saveData.put("coins_click", 1);
        saveData.put("multiplier", 1);

        this.db.collection("user_data")
                .document(username)
                .set(saveData);

        System.out.println("User created!");
    }

    public void saveUserData() {
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("username", UserDataHandler.getInstance().getUsername());
        saveData.put("password", UserDataHandler.getInstance().getPassword());
        saveData.put("coins", UserDataHandler.getInstance().getCoins());
        saveData.put("coins_click", UserDataHandler.getInstance().getClickValue());
        saveData.put("multiplier", UserDataHandler.getInstance().getMultiplier());

        this.db.collection("user_data")
                .document(UserDataHandler.getInstance().getUsername())
                .set(saveData);
    }

    public static DatabaseHandler getInstance() {
        if(instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }
}
