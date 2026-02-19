package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class zzbin {
    /* access modifiers changed from: private */
    public long zzglv = 43200;
    /* access modifiers changed from: private */
    public Map<String, String> zzglw;
    /* access modifiers changed from: private */
    public int zzglx;
    /* access modifiers changed from: private */
    public int zzglz = -1;
    /* access modifiers changed from: private */
    public int zzgma = -1;

    public final zzbin zzaa(long j) {
        this.zzglv = j;
        return this;
    }

    public final zzbim zzaok() {
        return new zzbim(this);
    }

    public final zzbin zzcj(int i) {
        this.zzglx = 10300;
        return this;
    }

    public final zzbin zzck(int i) {
        this.zzglz = i;
        return this;
    }

    public final zzbin zzcl(int i) {
        this.zzgma = i;
        return this;
    }

    public final zzbin zzx(String str, String str2) {
        if (this.zzglw == null) {
            this.zzglw = new HashMap();
        }
        this.zzglw.put(str, str2);
        return this;
    }
}
