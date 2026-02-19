package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzcfl extends zzcfp {
    private /* synthetic */ long zzitg;
    private /* synthetic */ PendingIntent zzith;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcfl(zzcfk zzcfk, GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzitg = j;
        this.zzith = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zzitg, this.zzith);
        setResult(Status.zzftq);
    }
}
