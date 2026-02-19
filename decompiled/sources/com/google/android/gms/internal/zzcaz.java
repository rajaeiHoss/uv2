package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.zzbj;

final class zzcaz extends zzbxu {
    private /* synthetic */ Subscription zzhnb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcaz(zzcaw zzcaw, GoogleApiClient googleApiClient, Subscription subscription) {
        super(googleApiClient);
        this.zzhnb = subscription;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxp zzbxp) throws RemoteException {
        ((zzbze) zzbxp.zzalw()).zza(new zzbj(this.zzhnb, false, (zzbzt) new zzcbq(this)));
    }
}
