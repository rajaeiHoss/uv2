package com.google.android.gms.internal;

final class zzbdu implements Runnable {
    private /* synthetic */ zzbdp zzfmz;
    private /* synthetic */ zzbdd zzfnc;

    zzbdu(zzbdr zzbdr, zzbdp zzbdp, zzbdd zzbdd) {
        this.zzfmz = zzbdp;
        this.zzfnc = zzbdd;
    }

    public final void run() {
        this.zzfmz.zza(this.zzfnc);
    }
}
