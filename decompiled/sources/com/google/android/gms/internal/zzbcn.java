package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

final class zzbcn implements Cast.MessageReceivedCallback {
    private /* synthetic */ zzbcm zzfku;

    zzbcn(zzbcm zzbcm) {
        this.zzfku = zzbcm;
    }

    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzfku.zzfkt.zzfu(str2);
    }
}
