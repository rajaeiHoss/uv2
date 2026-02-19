package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzg;

final class zzczq {
    public static void zza(String str, Throwable th, Context context) {
        zzdal.zzb(str, th);
        zzdal.v(zzg.zza(context, th) ? "Crash reported successfully." : "Failed to report crash");
    }

    public static void zzc(String str, Context context) {
        zzdal.e(str);
        zzdal.v(zzg.zza(context, new RuntimeException(str)) ? "Crash reported successfully." : "Failed to report crash");
    }

    public static void zzd(String str, Context context) {
        zzdal.zzcz(str);
        zzdal.v(zzg.zza(context, new RuntimeException(str)) ? "Crash reported successfully." : "Failed to report crash");
    }
}
