package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzboe extends zzbnp {
    private /* synthetic */ zzboa zzguo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzboe(zzboa zzboa, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzguo = zzboa;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmd(this.zzguo.zzgul.getRequestId(), false), (zzbrm) new zzbto(this));
    }
}
