package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;

final class zzbdv implements Runnable {
    private /* synthetic */ String zzetf;
    private /* synthetic */ zzbdp zzfmz;
    private /* synthetic */ String zzfnd;

    zzbdv(zzbdr zzbdr, zzbdp zzbdp, String str, String str2) {
        this.zzfmz = zzbdp;
        this.zzetf = str;
        this.zzfnd = str2;
    }

    public final void run() {
        Cast.MessageReceivedCallback messageReceivedCallback;
        synchronized (this.zzfmz.zzfmd) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) this.zzfmz.zzfmd.get(this.zzetf);
        }
        if (messageReceivedCallback != null) {
            messageReceivedCallback.onMessageReceived(this.zzfmz.zzfar, this.zzetf, this.zzfnd);
            return;
        }
        zzbdp.zzeus.zzb("Discarded message for unknown namespace '%s'", this.zzetf);
    }
}
