package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcrc extends zzcru {
    zzcrc(zzcqw zzcqw, GoogleApiClient googleApiClient) {
        super(googleApiClient, (zzcqx) null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.stopAllEndpoints();
    }
}
