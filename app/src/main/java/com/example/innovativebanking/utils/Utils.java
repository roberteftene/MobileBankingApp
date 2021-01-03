package com.example.innovativebanking.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public int getCurrentUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("currentUserID", 1);
    }

}
