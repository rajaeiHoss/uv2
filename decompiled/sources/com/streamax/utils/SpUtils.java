package com.streamax.utils;

import android.content.SharedPreferences;
import com.streamax.config.constant.Constants;
import java.util.ArrayList;

public class SpUtils {
    private static volatile SharedPreferences primaryPreferences;
    private static volatile SharedPreferences serverListPreferences;

    private static SharedPreferences getPrimaryPreferences() {
        if (primaryPreferences == null) {
            synchronized (SpUtils.class) {
                if (primaryPreferences == null) {
                    primaryPreferences = AppProxy.getContext().getSharedPreferences(Constants.SpName, 0);
                }
            }
        }
        return primaryPreferences;
    }

    private static SharedPreferences getServerListPreferences() {
        if (serverListPreferences == null) {
            synchronized (SpUtils.class) {
                if (serverListPreferences == null) {
                    serverListPreferences = AppProxy.getContext().getSharedPreferences(Constants.SpName1, 0);
                }
            }
        }
        return serverListPreferences;
    }

    public static void putInt(String key, int value) {
        primaryPreferences = getPrimaryPreferences();
        primaryPreferences.edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        primaryPreferences = getPrimaryPreferences();
        return primaryPreferences.getInt(key, defaultValue);
    }

    public static void putString(String key, String value) {
        primaryPreferences = getPrimaryPreferences();
        primaryPreferences.edit().putString(key, value).apply();
    }

    public static void removeString(String key) {
        primaryPreferences = getPrimaryPreferences();
        primaryPreferences.edit().remove(key).apply();
    }

    public static String getString(String key, String defaultValue) {
        primaryPreferences = getPrimaryPreferences();
        return primaryPreferences.getString(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        primaryPreferences = getPrimaryPreferences();
        primaryPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        primaryPreferences = getPrimaryPreferences();
        return primaryPreferences.getBoolean(key, defaultValue);
    }

    public static void setListData(ArrayList<String> serverList) {
        serverListPreferences = getServerListPreferences();
        SharedPreferences.Editor editor = serverListPreferences.edit();
        editor.putInt("servernums", serverList.size());
        for (int serverIndex = 0; serverIndex < serverList.size(); serverIndex++) {
            editor.putString("item_" + serverIndex, serverList.get(serverIndex));
        }
        editor.commit();
    }

    public static ArrayList<String> getListData() {
        ArrayList<String> serverList = new ArrayList<>();
        serverListPreferences = getServerListPreferences();
        int serverCount = serverListPreferences.getInt("servernums", 0);
        for (int serverIndex = 0; serverIndex < serverCount; serverIndex++) {
            SharedPreferences preferences = serverListPreferences;
            serverList.add(preferences.getString("item_" + serverIndex, (String) null));
        }
        return serverList;
    }
}
