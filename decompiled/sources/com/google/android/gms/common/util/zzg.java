package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveFile;

public final class zzg {
    private static final String[] zzgkk = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzgkl = null;
    private static boolean zzgkm = false;
    private static int zzgkn = -1;
    private static int zzgko = 0;

    public static boolean zza(Context context, Throwable th) {
        return zza(context, th, DriveFile.MODE_WRITE_ONLY);
    }

    private static boolean zza(Context context, Throwable th, int i) {
        try {
            zzbq.checkNotNull(context);
            zzbq.checkNotNull(th);
            return false;
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }
}
