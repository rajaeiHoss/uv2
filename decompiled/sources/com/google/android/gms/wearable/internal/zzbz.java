package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

final class zzbz extends zzn<DataItemBuffer> {
    zzbz(zzbw zzbw, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza(new zzgw(this));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ DataItemBuffer zzb(Status status) {
        return new DataItemBuffer(DataHolder.zzbz(status.getStatusCode()));
    }
}
