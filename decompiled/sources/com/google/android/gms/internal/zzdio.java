package com.google.android.gms.internal;

public final class zzdio {
    private final zzdjc zzkxo;
    private final byte[] zzlbn;
    private final long zzlbo;
    private final zzdia zzlbp;

    public zzdio(zzdia zzdia, byte[] bArr, zzdjc zzdjc, long j) {
        this.zzlbp = zzdia;
        this.zzlbn = bArr;
        this.zzkxo = zzdjc;
        this.zzlbo = j;
    }

    public zzdio(zzdjc zzdjc) {
        this((zzdia) null, (byte[]) null, zzdjc, 0);
    }

    public final byte[] zzbjx() {
        return this.zzlbn;
    }

    public final zzdia zzbjy() {
        return this.zzlbp;
    }

    public final zzdjc zzbjz() {
        return this.zzkxo;
    }

    public final long zzbka() {
        return this.zzlbo;
    }
}
