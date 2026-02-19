package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzcz extends zzbu<Integer, Long> {
    public long zzaif;
    public long zzaig;

    public zzcz() {
        this.zzaif = -1;
        this.zzaig = -1;
    }

    public zzcz(String str) {
        this();
        zzj(str);
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            this.zzaif = ((Long) zzk.get(0)).longValue();
            this.zzaig = ((Long) zzk.get(1)).longValue();
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzy() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, Long.valueOf(this.zzaif));
        hashMap.put(1, Long.valueOf(this.zzaig));
        return hashMap;
    }
}
