package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzax;

final class zzcbm extends zzbyg {
    private /* synthetic */ PendingIntent zzhnf;
    private /* synthetic */ int zzhnl = 0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbm(zzcbh zzcbh, GoogleApiClient googleApiClient, PendingIntent pendingIntent, int i) {
        super(googleApiClient);
        this.zzhnf = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new zzax(this.zzhnf, (zzbzt) new zzcbq(this), this.zzhnl));
    }
}
