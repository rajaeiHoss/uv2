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

    public static void log(Class cls, String message) {
    }

    public static void log(String message) {
    }

    public static void d(String message) {
        if (mDebuggable >= 2) {
            Log.d(mTag, message);
        }
    }

    public static void i(String message) {
        if (mDebuggable >= 3) {
            Log.i(mTag, message);
        }
    }

    public static void w(String message) {
        if (mDebuggable >= 4) {
            Log.w(mTag, message);
        }
    }

    public static void w(Throwable th) {
        if (mDebuggable >= 4) {
            Log.w(mTag, "", th);
        }
    }

    public static void w(String message, Throwable th) {
        if (mDebuggable >= 4 && message != null) {
            Log.w(mTag, message, th);
        }
    }

    public static void e(String message) {
        if (mDebuggable >= 5) {
            Log.e(mTag, message);
        }
    }

    public static void sf(String message) {
        if (mDebuggable >= 5) {
            PrintStream printStream = System.out;
            printStream.println("----------" + message + "----------");
        }
    }

    public static void s(String message) {
        if (mDebuggable >= 5) {
            System.out.println(message);
        }
    }

    public static void e(Throwable th) {
        if (mDebuggable >= 5) {
            Log.e(mTag, "", th);
        }
    }

    public static void e(String message, Throwable th) {
        if (mDebuggable >= 5 && message != null) {
            Log.e(mTag, message, th);
        }
    }

    public static void d(String tag, String message) {
        if (mDebuggable >= 2) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (mDebuggable >= 3) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (mDebuggable >= 4) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (mDebuggable >= 5) {
            Log.e(tag, message);
        }
    }
}
