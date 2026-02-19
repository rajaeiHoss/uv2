package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzm;

final class zzab extends zzm.zzf<zzac> {
    private /* synthetic */ PlaceReport zzixm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzz zzz, Api api, GoogleApiClient googleApiClient, PlaceReport placeReport) {
        super(api, googleApiClient);
        this.zzixm = placeReport;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzac zzac) throws RemoteException {
        zzac.zza(new zzm((zzm.zzf) this), this.zzixm);
    }
}
