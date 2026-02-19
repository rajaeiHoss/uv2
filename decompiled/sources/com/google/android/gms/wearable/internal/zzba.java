package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzba extends zzn<Status> {
    private /* synthetic */ int zzcdn;
    private /* synthetic */ zzay zzlti;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzay zzay, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
        this.zzlti = zzay;
        this.zzcdn = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zzb((zzek) new zzgo(this), this.zzlti.zzeia, this.zzcdn);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Status zzb(Status status) {
        return status;
    }
}
