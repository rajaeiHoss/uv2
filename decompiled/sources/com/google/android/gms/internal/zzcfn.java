package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.ActivityTransitionRequest;

final class zzcfn extends zzcfp {
    private /* synthetic */ PendingIntent zzhmu;
    private /* synthetic */ ActivityTransitionRequest zziti;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcfn(zzcfk zzcfk, GoogleApiClient googleApiClient, ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zziti = activityTransitionRequest;
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zziti, this.zzhmu, (zzn<Status>) this);
    }
}
