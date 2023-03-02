package com.coder.elaundry_apps.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.coder.elaundry_apps.api.Constants;
import com.google.gson.Gson;

public class SessionUtils {

    public static boolean login(Context context, String IdUser, String role, String latitude, String longitude){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userJson = new Gson().toJson(IdUser);
        editor.putString(Constants.KEY_SESSION, userJson);
        editor.putString("idUser", IdUser);
        editor.putString("role", role);
        editor.putString("latitude", latitude);
        editor.putString("longitude", longitude);
        editor.apply();
        return true;
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString(Constants.KEY_SESSION, null);
        return userJson != null;
    }
    public static boolean logout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constants.KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
