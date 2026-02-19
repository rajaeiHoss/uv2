package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.panorama.Panorama;

abstract class zzcvt<R extends Result> extends zzm<R, zzcvu> {
    protected zzcvt(GoogleApiClient googleApiClient) {
        super(Panorama.zzegu, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzcvl zzcvl) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzcvu zzcvu) throws RemoteException {
        zza(zzcvu.getContext(), (zzcvl) zzcvu.zzalw());
    }
}
