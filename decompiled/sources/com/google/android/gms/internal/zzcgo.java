package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.GeofencingRequest;

final class zzcgo extends zzcgq {
    private /* synthetic */ PendingIntent zzhmu;
    private /* synthetic */ GeofencingRequest zziua;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgo(zzcgn zzcgn, GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zziua = geofencingRequest;
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zziua, this.zzhmu, (zzn<Status>) this);
    }
}
