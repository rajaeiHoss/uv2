package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzw;

final class zzcar extends zzbxo {
    private /* synthetic */ PendingIntent zzhmu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcar(zzcam zzcam, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new zzw(this.zzhmu, new zzcbq(this)));
    }
}
