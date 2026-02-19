package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzbd;

final class zzcbn extends zzbyg {
    private /* synthetic */ PendingIntent zzhnf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbn(zzcbh zzcbh, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhnf = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new zzbd(this.zzhnf, (zzbzt) new zzcbq(this)));
    }
}
