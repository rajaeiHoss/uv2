package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzczl implements Runnable {
    private /* synthetic */ zzczg zzkvy;
    private final zzczu zzkvz;

    public zzczl(zzczg zzczg, zzczu zzczu) {
        this.zzkvy = zzczg;
        this.zzkvz = zzczu;
    }

    public final void run() {
        if (this.zzkvy.mState == 2) {
            String valueOf = String.valueOf(this.zzkvz.zzbio());
            zzdal.v(valueOf.length() != 0 ? "Evaluating tags for event ".concat(valueOf) : new String("Evaluating tags for event "));
            this.zzkvy.zzkvv.zzb(this.zzkvz);
        } else if (this.zzkvy.mState == 1) {
            this.zzkvy.zzkvw.add(this.zzkvz);
            String zzbio = this.zzkvz.zzbio();
            StringBuilder sb = new StringBuilder(String.valueOf(zzbio).length() + 30);
            sb.append("Added event ");
            sb.append(zzbio);
            sb.append(" to pending queue.");
            zzdal.v(sb.toString());
        } else if (this.zzkvy.mState == 3) {
            String zzbio2 = this.zzkvz.zzbio();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzbio2).length() + 61);
            sb2.append("Failed to evaluate tags for event ");
            sb2.append(zzbio2);
            sb2.append(" (container failed to load)");
            zzdal.v(sb2.toString());
            if (this.zzkvz.zzbir()) {
                try {
                    this.zzkvy.zzkvt.logEventInternalNoInterceptor("app", this.zzkvz.zzbio(), this.zzkvz.zzbip(), this.zzkvz.currentTimeMillis());
                    String zzbio3 = this.zzkvz.zzbio();
                    StringBuilder sb3 = new StringBuilder(String.valueOf(zzbio3).length() + 38);
                    sb3.append("Logged passthrough event ");
                    sb3.append(zzbio3);
                    sb3.append(" to Firebase.");
                    zzdal.v(sb3.toString());
                } catch (RemoteException e) {
                    zzczq.zza("Error logging event with measurement proxy:", e, this.zzkvy.mContext);
                }
            } else {
                String valueOf2 = String.valueOf(this.zzkvz.zzbio());
                zzdal.v(valueOf2.length() != 0 ? "Discarded non-passthrough event ".concat(valueOf2) : new String("Discarded non-passthrough event "));
            }
        }
    }
}
