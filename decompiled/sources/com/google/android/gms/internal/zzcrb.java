package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcrb extends zzcru {
    private /* synthetic */ String zzjyt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrb(zzcqw zzcqw, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.disconnectFromEndpoint(this.zzjyt);
    }
}
