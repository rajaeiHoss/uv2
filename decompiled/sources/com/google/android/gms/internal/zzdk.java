package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzdk extends zzbu<Integer, Object> {
    public Long zzail;
    public Boolean zzaim;
    public Boolean zzain;

    public zzdk() {
    }

    public zzdk(String str) {
        zzj(str);
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            this.zzail = (Long) zzk.get(0);
            this.zzaim = (Boolean) zzk.get(1);
            this.zzain = (Boolean) zzk.get(2);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Object> zzy() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzail);
        hashMap.put(1, this.zzaim);
        hashMap.put(2, this.zzain);
        return hashMap;
    }
}
