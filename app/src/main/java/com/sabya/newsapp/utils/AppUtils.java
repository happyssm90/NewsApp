package com.sabya.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Sabyasachi
 */
public class AppUtils {
    public static String getCurrentDate() {
        Date date = new Date();
        return DateFormat.getDateTimeInstance().format(date);
    }

    public static String getTempInCelsius(double kelvin) {
        float celsius = Math.round((float) (kelvin - 273.15F));
        return String.valueOf(celsius);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
