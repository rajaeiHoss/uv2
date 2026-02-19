package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzbo extends zzbv {
    private /* synthetic */ PendingIntent zzhmu;
    private /* synthetic */ zzbw zzkea;
    private /* synthetic */ SubscribeOptions zzkeb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbo(zzbi zzbi, GoogleApiClient googleApiClient, PendingIntent pendingIntent, zzbw zzbw, SubscribeOptions subscribeOptions) {
        super(googleApiClient);
        this.zzhmu = pendingIntent;
        this.zzkea = zzbw;
        this.zzkeb = subscribeOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), this.zzhmu, (zzab) this.zzkea, this.zzkeb);
    }
}
