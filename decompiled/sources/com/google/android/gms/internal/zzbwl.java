package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzj;

public final class zzbwl {
    private static int zzhly = -1;

    public static int zzdi(Context context) {
        int i;
        if (zzhly == -1) {
            if (zzj.zzcu(context)) {
                i = 3;
            } else {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback")) {
                    i = 0;
                } else {
                    if (zzj.zza(context.getResources()) && !zzdj(context)) {
                        i = 2;
                    } else {
                        boolean z = false;
                        if (!TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.startsWith("glass_")) {
                            z = true;
                        }
                        if (z) {
                            i = 6;
                        } else {
                            i = 1;
                        }
                    }
                }
            }
            zzhly = i;
        }
        return zzhly;
    }

    private static boolean zzdj(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType() != 0;
        } catch (Resources.NotFoundException e) {
            Log.wtf("Fitness", "Unable to determine type of device, assuming phone.", e);
            return true;
        }
    }
}
