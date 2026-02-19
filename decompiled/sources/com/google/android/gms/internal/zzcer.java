package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.instantapps.InstantApps;

abstract class zzcer<R extends Result> extends zzm<R, zzcet> {
    zzcer(GoogleApiClient googleApiClient) {
        super((Api<?>) InstantApps.API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzcef zzcef) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzcet zzcet) throws RemoteException {
        zza(zzcet.getContext(), (zzcef) zzcet.zzalw());
    }
}
