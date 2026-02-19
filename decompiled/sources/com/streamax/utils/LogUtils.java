package com.streamax.utils;

import android.util.Log;
import java.io.PrintStream;

public class LogUtils {
    public static final int LEVEL_ALL = 7;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_OFF = 0;
    public static final int LEVEL_SYSTEM = 6;
    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_WARN = 4;
    private static int mDebuggable = 7;
    private static String mTag = "DeviceConfig";

    public static void log(Class cls, String str) {
    }

    public static void log(String str) {
    }

    public static void d(String str) {
        if (mDebuggable >= 2) {
            Log.d(mTag, str);
        }
    }

    public static void i(String str) {
        if (mDebuggable >= 3) {
            Log.i(mTag, str);
        }
    }

    public static void w(String str) {
        if (mDebuggable >= 4) {
            Log.w(mTag, str);
        }
    }

    public static void w(Throwable th) {
        if (mDebuggable >= 4) {
            Log.w(mTag, "", th);
        }
    }

    public static void w(String str, Throwable th) {
        if (mDebuggable >= 4 && str != null) {
            Log.w(mTag, str, th);
        }
    }

    public static void e(String str) {
        if (mDebuggable >= 5) {
            Log.e(mTag, str);
        }
    }

    public static void sf(String str) {
        if (mDebuggable >= 5) {
            PrintStream printStream = System.out;
            printStream.println("----------" + str + "----------");
        }
    }

    public static void s(String str) {
        if (mDebuggable >= 5) {
            System.out.println(str);
        }
    }

    public static void e(Throwable th) {
        if (mDebuggable >= 5) {
            Log.e(mTag, "", th);
        }
    }

    public static void e(String str, Throwable th) {
        if (mDebuggable >= 5 && str != null) {
            Log.e(mTag, str, th);
        }
    }

    public static void d(String str, String str2) {
        if (mDebuggable >= 2) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (mDebuggable >= 3) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (mDebuggable >= 4) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (mDebuggable >= 5) {
            Log.e(str, str2);
        }
    }
}
