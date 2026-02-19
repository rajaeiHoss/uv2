package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

final class zzew extends zzn<Status> {
    private /* synthetic */ MessageApi.MessageListener zzluv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzew(zzeu zzeu, GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener) {
        super(googleApiClient);
        this.zzluv = messageListener;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<Status>) this, this.zzluv);
    }

    public final /* synthetic */ Status zzb(Status status) {
        return status;
    }
}
