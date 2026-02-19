package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbqs extends zzbnp {
    private /* synthetic */ zzbql zzgwm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbqs(zzbql zzbql, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzgwm = zzbql;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtt(this.zzgwm.zzgpe), (zzbrm) new zzbto(this));
    }
}
