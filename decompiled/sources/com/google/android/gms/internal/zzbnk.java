package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzbnk extends zzbnp {
    zzbnk(GoogleApiClient googleApiClient, Status status) {
        super(googleApiClient);
        setResult(status);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
    }
}
