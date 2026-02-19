package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzcaf extends zzbxb<DataTypeResult> {
    private /* synthetic */ DataTypeCreateRequest zzhml;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcaf(zzcae zzcae, GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest) {
        super(googleApiClient);
        this.zzhml = dataTypeCreateRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbwy zzbwy) throws RemoteException {
        ((zzbyy) zzbwy.zzalw()).zza(new DataTypeCreateRequest(this.zzhml, (zzbyq) new zzcai(this, (zzcaf) null)));
    }

    /* access modifiers changed from: protected */
    public final DataTypeResult zzb(Status status) {
        return DataTypeResult.zzaf(status);
    }
}
