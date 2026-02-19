package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;

final class zzcaq extends zzbxo {
    private /* synthetic */ DataUpdateListenerRegistrationRequest zzhmt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcaq(zzcam zzcam, GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        super(googleApiClient);
        this.zzhmt = dataUpdateListenerRegistrationRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new DataUpdateListenerRegistrationRequest(this.zzhmt, (IBinder) new zzcbq(this)));
    }
}
