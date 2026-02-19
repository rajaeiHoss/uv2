package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaz extends zzn<Status> {
    private /* synthetic */ zzay zzlti;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(zzay zzay, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzlti = zzay;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zzc(new zzgn(this), this.zzlti.zzeia);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Status zzb(Status status) {
        return status;
    }
}
