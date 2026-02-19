package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdbo;

final class zzdca implements Runnable {
    private /* synthetic */ String zzkzc;
    private /* synthetic */ boolean zzkzi;
    private /* synthetic */ zzdbo.zzb zzkzj;

    zzdca(zzdbo.zzb zzb, boolean z, String str) {
        this.zzkzj = zzb;
        this.zzkzi = z;
        this.zzkzc = str;
    }

    public final void run() {
        this.zzkzj.zzb(this.zzkzi, this.zzkzc);
    }
}
