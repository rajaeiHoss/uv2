package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzcfm extends zzcfp {
    private /* synthetic */ PendingIntent zzith;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcfm(zzcfk zzcfk, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzith = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zzc(this.zzith);
        setResult(Status.zzftq);
    }
}
