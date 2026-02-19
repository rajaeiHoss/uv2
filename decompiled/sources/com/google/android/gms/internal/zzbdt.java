package com.google.android.gms.internal;

final class zzbdt implements Runnable {
    private /* synthetic */ zzbdp zzfmz;
    private /* synthetic */ zzbdx zzfnb;

    zzbdt(zzbdr zzbdr, zzbdp zzbdp, zzbdx zzbdx) {
        this.zzfmz = zzbdp;
        this.zzfnb = zzbdx;
    }

    public final void run() {
        this.zzfmz.zza(this.zzfnb);
    }
}
