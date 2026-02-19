package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

public abstract class zzccv {
    private final AtomicReference<zzcct> zzibv = new AtomicReference<>();

    public final void flush() {
        zzcct zzcct = this.zzibv.get();
        if (zzcct != null) {
            zzcct.flush();
        }
    }

    /* access modifiers changed from: protected */
    public abstract zzcct zzaus();

    public final void zzp(String str, int i) {
        zzcct zzcct = this.zzibv.get();
        if (zzcct == null) {
            zzcct = zzaus();
            if (!this.zzibv.compareAndSet(null, zzcct)) {
                zzcct = this.zzibv.get();
            }
        }
        zzcct.zzv(str, i);
    }
}
