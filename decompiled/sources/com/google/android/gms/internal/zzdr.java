package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzdr extends zzbu<Integer, Long> {
    public Long zzajl;
    public Long zzajm;
    public Long zzajn;
    public Long zzajo;
    public Long zzajp;
    public Long zzajq;
    public Long zzajr;
    public Long zzfj;
    public Long zzfl;
    public Long zzfp;
    public Long zzfq;

    public zzdr() {
    }

    public zzdr(String str) {
        zzj(str);
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            this.zzajl = (Long) zzk.get(0);
            this.zzajm = (Long) zzk.get(1);
            this.zzajn = (Long) zzk.get(2);
            this.zzfl = (Long) zzk.get(3);
            this.zzfj = (Long) zzk.get(4);
            this.zzajo = (Long) zzk.get(5);
            this.zzajp = (Long) zzk.get(6);
            this.zzajq = (Long) zzk.get(7);
            this.zzfq = (Long) zzk.get(8);
            this.zzfp = (Long) zzk.get(9);
            this.zzajr = (Long) zzk.get(10);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzy() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzajl);
        hashMap.put(1, this.zzajm);
        hashMap.put(2, this.zzajn);
        hashMap.put(3, this.zzfl);
        hashMap.put(4, this.zzfj);
        hashMap.put(5, this.zzajo);
        hashMap.put(6, this.zzajp);
        hashMap.put(7, this.zzajq);
        hashMap.put(8, this.zzfq);
        hashMap.put(9, this.zzfp);
        hashMap.put(10, this.zzajr);
        return hashMap;
    }
}
