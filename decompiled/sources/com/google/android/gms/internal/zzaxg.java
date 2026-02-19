package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzaxg<R extends Result> extends zzm<R, zzaxi> {
    zzaxg(GoogleApiClient googleApiClient) {
        super((Api<?>) Auth.CREDENTIALS_API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzaxn zzaxn) throws DeadObjectException, RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzaxi zzaxi) throws RemoteException {
        zza(zzaxi.getContext(), (zzaxn) zzaxi.zzalw());
    }
}
