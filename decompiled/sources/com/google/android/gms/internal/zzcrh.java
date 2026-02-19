package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcrh extends zzcru {
    private /* synthetic */ String zzjyt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrh(zzcqw zzcqw, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zzj(this, this.zzjyt);
    }
}
