package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzak;
import com.google.android.gms.ads.internal.zzbt;

final class zzur {
    zzak zzceg;
    zzkk zzceh;
    zztl zzcei;
    long zzcej;
    boolean zzcek;
    boolean zzcel;
    private /* synthetic */ zzuq zzcem;

    zzur(zzuq zzuq, zztk zztk) {
        this.zzcem = zzuq;
        this.zzceg = zztk.zzaw(zzuq.zzapp);
        zztl zztl = new zztl();
        this.zzcei = zztl;
        zzak zzak = this.zzceg;
        zzak.zza((zzli) new zztm(zztl));
        zzak.zza((zzly) new zztu(zztl));
        zzak.zza((zzpb) new zztw(zztl));
        zzak.zza((zzlf) new zzty(zztl));
        zzak.zza((zzafc) new zzua(zztl));
    }

    zzur(zzuq zzuq, zztk zztk, zzkk zzkk) {
        this(zzuq, zztk);
        this.zzceh = zzkk;
    }

    /* access modifiers changed from: package-private */
    public final boolean load() {
        if (this.zzcek) {
            return false;
        }
        zzkk zzkk = this.zzceh;
        if (zzkk == null) {
            zzkk = this.zzcem.zzced;
        }
        this.zzcel = this.zzceg.zzb(zzuo.zzi(zzkk));
        this.zzcek = true;
        this.zzcej = zzbt.zzes().currentTimeMillis();
        return true;
    }
}
