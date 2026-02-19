package com.google.android.gms.internal;

final class zzbds implements Runnable {
    private /* synthetic */ zzbdp zzfmz;
    private /* synthetic */ int zzfna;

    zzbds(zzbdr zzbdr, zzbdp zzbdp, int i) {
        this.zzfmz = zzbdp;
        this.zzfna = i;
    }

    public final void run() {
        this.zzfmz.zzetn.onApplicationDisconnected(this.zzfna);
    }
}
