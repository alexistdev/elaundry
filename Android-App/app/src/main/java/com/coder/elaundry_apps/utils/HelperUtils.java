package com.coder.elaundry_apps.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtils {


    public static void pesan(Context mContext, String message)
    {
        Toast.makeText( mContext , message, Toast.LENGTH_LONG).show();
    }

    public static boolean cekEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
