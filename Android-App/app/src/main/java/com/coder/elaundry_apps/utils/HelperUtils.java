package com.coder.elaundry_apps.utils;

import android.content.Context;
import android.widget.Toast;

public class HelperUtils {


    public static void pesan(Context mContext, String message)
    {
        Toast.makeText( mContext , message, Toast.LENGTH_LONG).show();
    }
}
