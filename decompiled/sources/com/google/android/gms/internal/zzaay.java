package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;

final class zzaay implements zzalf<zzaof> {
    private /* synthetic */ String zzcqs;
    private /* synthetic */ zzt zzcqt;

    zzaay(zzaar zzaar, String str, zzt zzt) {
        this.zzcqs = str;
        this.zzcqt = zzt;
    }

    public final void onSuccess(zzaof zzaof) {
        zzaof.zzb(this.zzcqs, (zzt<? super zzaof>) this.zzcqt);
    }

    public final void zzb(Throwable th) {
    }
}
