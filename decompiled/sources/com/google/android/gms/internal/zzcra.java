package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcra extends zzcru {
    private /* synthetic */ long zzjyw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcra(zzcqw zzcqw, GoogleApiClient googleApiClient, long j) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyw = j;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza(this, this.zzjyw);
    }
}
