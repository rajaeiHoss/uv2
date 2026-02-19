package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

final class zzcht extends LocationServices.zza<LocationSettingsResult> {
    private /* synthetic */ LocationSettingsRequest zziuv;
    private /* synthetic */ String zziuw = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcht(zzchs zzchs, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        this.zziuv = locationSettingsRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zziuv, (zzn<LocationSettingsResult>) this, this.zziuw);
    }

    public final LocationSettingsResult zzb(Status status) {
        return new LocationSettingsResult(status);
    }
}
