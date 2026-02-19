package com.google.android.gms.internal;

final class zzdit implements Runnable {
    private /* synthetic */ String zzlbr;
    private /* synthetic */ zzdip zzlbt;
    private /* synthetic */ byte[] zzlbv;

    zzdit(zzdip zzdip, String str, byte[] bArr) {
        this.zzlbt = zzdip;
        this.zzlbr = str;
        this.zzlbv = bArr;
    }

    public final void run() {
        this.zzlbt.zze(this.zzlbr, this.zzlbv);
    }
}
