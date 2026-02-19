package com.google.android.gms.internal;

final class zzdis implements Runnable {
    private /* synthetic */ String zzlbr;
    private /* synthetic */ zzdid zzlbs;
    private /* synthetic */ zzdip zzlbt;
    private /* synthetic */ String zzlbu;

    zzdis(zzdip zzdip, String str, String str2, zzdid zzdid) {
        this.zzlbt = zzdip;
        this.zzlbr = str;
        this.zzlbu = str2;
        this.zzlbs = zzdid;
    }

    public final void run() {
        this.zzlbt.zzb(this.zzlbr, this.zzlbu, this.zzlbs);
    }
}
