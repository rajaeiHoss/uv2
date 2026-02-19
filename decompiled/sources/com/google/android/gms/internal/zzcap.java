package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;

final class zzcap extends zzbxo {
    private /* synthetic */ DataUpdateRequest zzhms;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcap(zzcam zzcam, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        super(googleApiClient);
        this.zzhms = dataUpdateRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new DataUpdateRequest(this.zzhms, (IBinder) new zzcbq(this)));
    }
}
