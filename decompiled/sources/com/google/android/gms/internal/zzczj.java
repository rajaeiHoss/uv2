package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.List;

final class zzczj implements Runnable {
    private /* synthetic */ zzczg zzkvy;

    private zzczj(zzczg zzczg) {
        this.zzkvy = zzczg;
    }

    /* synthetic */ zzczj(zzczg zzczg, zzczh zzczh) {
        this(zzczg);
    }

    public final void run() {
        int unused = this.zzkvy.mState = 3;
        String zzd = this.zzkvy.zzknc;
        StringBuilder sb = new StringBuilder(String.valueOf(zzd).length() + 26);
        sb.append("Container ");
        sb.append(zzd);
        sb.append(" loading failed.");
        zzdal.zzcz(sb.toString());
        if (this.zzkvy.zzkvw != null) {
            for (zzczu zzczu : this.zzkvy.zzkvw) {
                if (zzczu.zzbir()) {
                    try {
                        this.zzkvy.zzkvt.logEventInternalNoInterceptor("app", zzczu.zzbio(), zzczu.zzbip(), zzczu.currentTimeMillis());
                        String zzbio = zzczu.zzbio();
                        StringBuilder sb2 = new StringBuilder(String.valueOf(zzbio).length() + 50);
                        sb2.append("Logged event ");
                        sb2.append(zzbio);
                        sb2.append(" to Firebase (marked as passthrough).");
                        zzdal.v(sb2.toString());
                    } catch (RemoteException e) {
                        zzczq.zza("Error logging event with measurement proxy:", e, this.zzkvy.mContext);
                    }
                } else {
                    String zzbio2 = zzczu.zzbio();
                    StringBuilder sb3 = new StringBuilder(String.valueOf(zzbio2).length() + 45);
                    sb3.append("Discarded event ");
                    sb3.append(zzbio2);
                    sb3.append(" (marked as non-passthrough).");
                    zzdal.v(sb3.toString());
                }
            }
            List unused2 = this.zzkvy.zzkvw = null;
        }
    }
}
