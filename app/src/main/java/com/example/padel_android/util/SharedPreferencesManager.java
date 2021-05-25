package com.example.padel_android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.padel_android.MainActivity;

public class SharedPreferencesManager {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPreferencesManager(Context context){
        sp = context.getSharedPreferences(MainActivity.APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void save (String email, String password){
        spEditor.putString(MainActivity.EMAIL, email);
        spEditor.putString(MainActivity.PASSWORD, password);
        spEditor.apply();
    }

    public void save (String email, String password, String token){
        spEditor.putString(MainActivity.EMAIL, email);
        spEditor.putString(MainActivity.PASSWORD, password);
        spEditor.putString(MainActivity.TOKEN, token);
        spEditor.apply();
    }
    public void saveEmail(String key, String value){
        spEditor.putString(key, value);
        spEditor.apply();
    }

    public String getEmail(){
        return sp.getString(MainActivity.EMAIL, "");
    }

    public void savePassword(String key, String value){
        spEditor.putString(key, value);
        spEditor.apply();
    }

    public String getPassword(){
        return sp.getString(MainActivity.PASSWORD, "");
    }

    public void saveToken(String key, String value){
        spEditor.putString(key, value);
        spEditor.apply();
    }

    public String getToken(){
        return sp.getString(MainActivity.TOKEN, "");
    }
}

