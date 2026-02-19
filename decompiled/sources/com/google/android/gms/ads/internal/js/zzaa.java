package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzamh;
import com.google.android.gms.internal.zzamj;

public final class zzaa extends zzamj<zzaj> {
    private final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final zzae zzcga;
    private boolean zzcgb;

    public zzaa(zzae zzae) {
        this.zzcga = zzae;
    }

    public final void release() {
        synchronized (this.mLock) {
            if (!this.zzcgb) {
                this.zzcgb = true;
                zza(new zzab(this), new zzamh());
                zza(new zzac(this), new zzad(this));
            }
        }
    }
}
