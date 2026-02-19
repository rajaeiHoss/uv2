package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbov extends zzbnp {
    private /* synthetic */ zzbre zzgtn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbov(zzbot zzbot, GoogleApiClient googleApiClient, zzbre zzbre) {
        super(googleApiClient);
        this.zzgtn = zzbre;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtk(this.zzgtn), (zzbrm) new zzbto(this));
    }
}
