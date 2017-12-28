package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 21/11/17.
 */
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class for Shared Preference
 */
public class PrefManager {

    Context context;

    PrefManager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password, String kid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.putString("kidId",kid);
        editor.commit();
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
    public void saveLogoutDetails() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", "");
        editor.putString("Password", "");
        editor.putString("kidId", "");
        editor.commit();

}}