package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbiv<R extends Result> extends zzm<R, zzbja> {
    public zzbiv(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbii.API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzbjl zzbjl) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzbja zzbja) throws RemoteException {
        zza(zzbja.getContext(), (zzbjl) zzbja.zzalw());
    }
}
