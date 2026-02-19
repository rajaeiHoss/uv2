package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbq extends zzbv {
    private /* synthetic */ PendingIntent zzhmu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbq(zzbi zzbi, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), this.zzhmu);
    }
}
