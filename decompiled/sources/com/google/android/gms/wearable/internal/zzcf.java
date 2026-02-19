package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;

final class zzcf extends zzn<Status> {
    private /* synthetic */ DataApi.DataListener zzlty;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcf(zzbw zzbw, GoogleApiClient googleApiClient, DataApi.DataListener dataListener) {
        super(googleApiClient);
        this.zzlty = dataListener;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<Status>) this, this.zzlty);
    }

    public final Status zzb(Status status) {
        return status;
    }
}
