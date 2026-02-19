package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzami;

final class zzw implements zzami<zzc> {
    private /* synthetic */ zzn zzcfu;
    private /* synthetic */ zzae zzcfx;

    zzw(zzn zzn, zzae zzae) {
        this.zzcfu = zzn;
        this.zzcfx = zzae;
    }

    public final void zze(zzc zzc) {
        synchronized (this.zzcfu.mLock) {
            int unused = this.zzcfu.zzcfl = 0;
            if (!(this.zzcfu.zzcfk == null || this.zzcfx == this.zzcfu.zzcfk)) {
                zzahw.v("New JS engine is loaded, marking previous one as destroyable.");
                this.zzcfu.zzcfk.zzmc();
            }
            zzae unused2 = this.zzcfu.zzcfk = this.zzcfx;
        }
    }
}
