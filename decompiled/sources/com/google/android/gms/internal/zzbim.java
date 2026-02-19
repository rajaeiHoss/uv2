package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzbim {
    private final long zzglv;
    private final Map<String, String> zzglw;
    private final int zzglx;
    private final List<zzbip> zzgly;
    private final int zzglz;
    private final int zzgma;

    zzbim(zzbin zzbin) {
        this.zzglv = zzbin.zzglv;
        this.zzglw = zzbin.zzglw;
        this.zzglx = zzbin.zzglx;
        this.zzgly = null;
        this.zzglz = zzbin.zzglz;
        this.zzgma = zzbin.zzgma;
    }

    public final long zzaof() {
        return this.zzglv;
    }

    public final Map<String, String> zzaog() {
        Map<String, String> map = this.zzglw;
        return map == null ? Collections.emptyMap() : map;
    }

    public final int zzaoh() {
        return this.zzglx;
    }

    public final int zzaoi() {
        return this.zzgma;
    }

    public final int zzaoj() {
        return this.zzglz;
    }
}
