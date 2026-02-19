package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzcas extends zzbxm<DataReadResult> {
    private /* synthetic */ DataReadRequest zzhmv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcas(zzcam zzcam, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.zzhmv = dataReadRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new DataReadRequest(this.zzhmv, (zzbyk) new zzcav(this, (zzcan) null)));
    }

    /* access modifiers changed from: protected */
    public final DataReadResult zzb(Status status) {
        return DataReadResult.zza(status, this.zzhmv.getDataTypes(), this.zzhmv.getDataSources());
    }
}
