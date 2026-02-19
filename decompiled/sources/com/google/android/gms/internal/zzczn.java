package com.google.android.gms.internal;

import java.util.List;

final class zzczn implements Runnable {
    private /* synthetic */ zzczg zzkvy;
    private final zzdin zzkwa;

    zzczn(zzczg zzczg, zzdin zzdin) {
        this.zzkvy = zzczg;
        this.zzkwa = zzdin;
    }

    public final void run() {
        zzdjc zzbjz = this.zzkwa.zzbju().zzbjz();
        zzdjk zzbjv = this.zzkwa.zzbjv();
        boolean z = this.zzkvy.zzkvv == null;
        zzczg zzczg = this.zzkvy;
        zzdav unused = zzczg.zzkvv = zzczg.zzkvp.zza(zzbjz, zzbjv);
        int unused2 = this.zzkvy.mState = 2;
        String zzd = this.zzkvy.zzknc;
        StringBuilder sb = new StringBuilder(String.valueOf(zzd).length() + 48);
        sb.append("Container ");
        sb.append(zzd);
        sb.append(" loaded during runtime initialization.");
        zzdal.v(sb.toString());
        if (this.zzkvy.zzkvw != null) {
            for (zzczu zzczu : this.zzkvy.zzkvw) {
                String valueOf = String.valueOf(zzczu.zzbio());
                zzdal.v(valueOf.length() != 0 ? "Evaluating tags for pending event ".concat(valueOf) : new String("Evaluating tags for pending event "));
                this.zzkvy.zzkvv.zzb(zzczu);
            }
            List unused3 = this.zzkvy.zzkvw = null;
        }
        this.zzkvy.zzkvv.dispatch();
        String valueOf2 = String.valueOf(this.zzkvy.zzknc);
        zzdal.v(valueOf2.length() != 0 ? "Runtime initialized successfully for container ".concat(valueOf2) : new String("Runtime initialized successfully for container "));
        long zzbka = this.zzkwa.zzbju().zzbka() + this.zzkvy.zzkvu.zzbfw();
        if (!z || !this.zzkvy.zzkvx || this.zzkwa.getSource() != 1 || zzbka >= this.zzkvy.zzata.currentTimeMillis()) {
            zzczg zzczg2 = this.zzkvy;
            zzczg2.zzbj(Math.max(900000, zzbka - zzczg2.zzata.currentTimeMillis()));
            return;
        }
        zzczg zzczg3 = this.zzkvy;
        zzczg3.zzbj(zzczg3.zzkvu.zzbim());
    }
}
