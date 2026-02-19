package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;

final class zzcao extends zzbxo {
    private /* synthetic */ DataDeleteRequest zzhmr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcao(zzcam zzcam, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        super(googleApiClient);
        this.zzhmr = dataDeleteRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new DataDeleteRequest(this.zzhmr, (zzbzt) new zzcbq(this)));
    }
}
