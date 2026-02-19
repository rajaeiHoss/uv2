package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.zzaz;

final class zzcbi extends zzbyg {
    private /* synthetic */ Session zzhnh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbi(zzcbh zzcbh, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.zzhnh = session;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new zzaz(this.zzhnh, (zzbzt) new zzcbq(this)));
    }
}
