package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbou extends zzboy {
    private /* synthetic */ zzbot zzgvd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbou(zzbot zzbot, GoogleApiClient googleApiClient) {
        super(zzbot, googleApiClient);
        this.zzgvd = zzbot;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zzb(new zzbow(this.zzgvd, this, (zzbou) null));
    }
}
