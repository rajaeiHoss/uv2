package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import java.util.Collections;

final class zzcbe extends zzbxy<DataSourcesResult> {
    private /* synthetic */ DataSourcesRequest zzhnd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbe(zzcbd zzcbd, GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        super(googleApiClient);
        this.zzhnd = dataSourcesRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxv zzbxv) throws RemoteException {
        ((zzbzg) zzbxv.zzalw()).zza(new DataSourcesRequest(this.zzhnd, (zzbyn) new zzbwr(this)));
    }

    /* access modifiers changed from: protected */
    public final DataSourcesResult zzb(Status status) {
        return new DataSourcesResult(Collections.emptyList(), status);
    }
}
