package com.google.android.gms.internal;

final class zzdir implements Runnable {
    private /* synthetic */ String zzlbr;
    private /* synthetic */ zzdid zzlbs;
    private /* synthetic */ zzdip zzlbt;

    zzdir(zzdip zzdip, String str, zzdid zzdid) {
        this.zzlbt = zzdip;
        this.zzlbr = str;
        this.zzlbs = zzdid;
    }

    public final void run() {
        this.zzlbt.zzb(this.zzlbr, this.zzlbs);
    }
}
