package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbmx extends zzbnp {
    private /* synthetic */ zzbre zzgtn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbmx(zzbmu zzbmu, GoogleApiClient googleApiClient, zzbre zzbre) {
        super(googleApiClient);
        this.zzgtn = zzbre;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtk(this.zzgtn), (zzbrm) new zzbto(this));
    }
}
