package com.streamax.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import com.streamax.client.MyApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UiUtils {
    private static ArrayList<String> mDatas;

    public static Context getContext() {
        return MyApp.getContext();
    }

    public static Handler getHandler() {
        return MyApp.getHandler();
    }

    public static int getMainThreadId() {
        return MyApp.getMainThreadId();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String[] getStrArray(int i) {
        return getResources().getStringArray(i);
    }

    public static int[] getIntArray(int i) {
        return getResources().getIntArray(i);
    }

    public static List<Integer> getIntData(int i) {
        if (i >= 0) {
            int[] intArray = getIntArray(i);
            Objects.requireNonNull(intArray);
            ArrayList arrayList = new ArrayList();
            for (int valueOf : intArray) {
                arrayList.add(Integer.valueOf(valueOf));
            }
            return arrayList;
        }
        throw new IllegalArgumentException();
    }

    public static String getString(int i) {
        return getResources().getString(i);
    }

    public static List<String> getStrData(int i) {
        if (i >= 0) {
            String[] strArray = getStrArray(i);
            Objects.requireNonNull(strArray);
            ArrayList arrayList = new ArrayList();
            for (String add : strArray) {
                arrayList.add(add);
            }
            return arrayList;
        }
        throw new IllegalArgumentException();
    }

    public static ArrayList<String> getStrDatas(String[] strArr) {
        Objects.requireNonNull(strArr);
        mDatas = new ArrayList<>();
        for (String add : strArr) {
            mDatas.add(add);
        }
        return mDatas;
    }

    public static boolean isCNLan() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    public static int getInt(int i) {
        return getResources().getInteger(i);
    }
}
