package com.streamax.utils;

import android.content.SharedPreferences;
import com.streamax.config.constant.Constants;
import java.util.ArrayList;

public class SpUtils {
    private static volatile SharedPreferences preferences;
    private static volatile SharedPreferences preferences1;

    private static SharedPreferences getSp() {
        if (preferences == null) {
            synchronized (SpUtils.class) {
                if (preferences == null) {
                    preferences = AppProxy.getContext().getSharedPreferences(Constants.SpName, 0);
                }
            }
        }
        return preferences;
    }

    private static SharedPreferences getSps() {
        if (preferences1 == null) {
            synchronized (SpUtils.class) {
                if (preferences1 == null) {
                    preferences1 = AppProxy.getContext().getSharedPreferences(Constants.SpName1, 0);
                }
            }
        }
        return preferences1;
    }

    public static void putInt(String str, int i) {
        preferences = getSp();
        preferences.edit().putInt(str, i).apply();
    }

    public static int getInt(String str, int i) {
        preferences = getSp();
        return preferences.getInt(str, i);
    }

    public static void putString(String str, String str2) {
        preferences = getSp();
        preferences.edit().putString(str, str2).apply();
    }

    public static void removeString(String str) {
        preferences = getSp();
        preferences.edit().remove(str).apply();
    }

    public static String getString(String str, String str2) {
        preferences = getSp();
        return preferences.getString(str, str2);
    }

    public static void putBoolean(String str, boolean z) {
        preferences = getSp();
        preferences.edit().putBoolean(str, z).apply();
    }

    public static boolean getBoolean(String str, boolean z) {
        preferences = getSp();
        return preferences.getBoolean(str, z);
    }

    public static void setListData(ArrayList<String> arrayList) {
        preferences1 = getSps();
        SharedPreferences.Editor edit = preferences1.edit();
        edit.putInt("servernums", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            edit.putString("item_" + i, arrayList.get(i));
        }
        edit.commit();
    }

    public static ArrayList<String> getListData() {
        ArrayList<String> arrayList = new ArrayList<>();
        preferences1 = getSps();
        int i = preferences1.getInt("servernums", 0);
        for (int i2 = 0; i2 < i; i2++) {
            SharedPreferences sharedPreferences = preferences1;
            arrayList.add(sharedPreferences.getString("item_" + i2, (String) null));
        }
        return arrayList;
    }
}
