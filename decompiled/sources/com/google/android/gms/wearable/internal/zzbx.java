package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.PutDataRequest;

final class zzbx extends zzn<DataApi.DataItemResult> {
    private /* synthetic */ PutDataRequest zzltu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbx(zzbw zzbw, GoogleApiClient googleApiClient, PutDataRequest putDataRequest) {
        super(googleApiClient);
        this.zzltu = putDataRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<DataApi.DataItemResult>) this, this.zzltu);
    }

    public final DataApi.DataItemResult zzb(Status status) {
        return new zzcg(status, (DataItem) null);
    }
}
