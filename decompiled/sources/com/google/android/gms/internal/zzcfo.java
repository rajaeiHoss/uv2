package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzcfo extends zzcfp {
    private /* synthetic */ PendingIntent zzhmu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcfo(zzcfk zzcfk, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zzhmu, (zzn<Status>) this);
    }
}
