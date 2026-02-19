package com.hjq.base.action;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

public interface BundleAction {
    boolean getBoolean(String str);

    boolean getBoolean(String str, boolean z);

    Bundle getBundle();

    double getDouble(String str);

    double getDouble(String str, int i);

    float getFloat(String str);

    float getFloat(String str, int i);

    int getInt(String str);

    int getInt(String str, int i);

    ArrayList<Integer> getIntegerArrayList(String str);

    long getLong(String str);

    long getLong(String str, int i);

    <P extends Parcelable> P getParcelable(String str);

    <S extends Serializable> S getSerializable(String str);

    String getString(String str);

    ArrayList<String> getStringArrayList(String str);

    /* renamed from: com.hjq.base.action.BundleAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$getInt(BundleAction _this, String str) {
            return _this.getInt(str, 0);
        }

        public static int $default$getInt(BundleAction _this, String str, int i) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return i;
            }
            return bundle.getInt(str, i);
        }

        public static long $default$getLong(BundleAction _this, String str) {
            return _this.getLong(str, 0);
        }

        public static long $default$getLong(BundleAction _this, String str, int i) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return (long) i;
            }
            return bundle.getLong(str, (long) i);
        }

        public static float $default$getFloat(BundleAction _this, String str) {
            return _this.getFloat(str, 0);
        }

        public static float $default$getFloat(BundleAction _this, String str, int i) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return (float) i;
            }
            return bundle.getFloat(str, (float) i);
        }

        public static double $default$getDouble(BundleAction _this, String str) {
            return _this.getDouble(str, 0);
        }

        public static double $default$getDouble(BundleAction _this, String str, int i) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return (double) i;
            }
            return bundle.getDouble(str, (double) i);
        }

        public static boolean $default$getBoolean(BundleAction _this, String str) {
            return _this.getBoolean(str, false);
        }

        public static boolean $default$getBoolean(BundleAction _this, String str, boolean z) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return z;
            }
            return bundle.getBoolean(str, z);
        }

        public static String $default$getString(BundleAction _this, String str) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getString(str);
        }

        public static <P extends Parcelable> Parcelable $default$getParcelable(BundleAction _this, String str) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getParcelable(str);
        }

        public static <S extends Serializable> Serializable $default$getSerializable(BundleAction _this, String str) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getSerializable(str);
        }

        public static ArrayList $default$getStringArrayList(BundleAction _this, String str) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getStringArrayList(str);
        }

        public static ArrayList $default$getIntegerArrayList(BundleAction _this, String str) {
            Bundle bundle = _this.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getIntegerArrayList(str);
        }
    }
}
