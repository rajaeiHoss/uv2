package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbnt extends zzbnp {
    private /* synthetic */ zzbly zzguc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbnt(zzbnq zzbnq, GoogleApiClient googleApiClient, zzbly zzbly) {
        super(googleApiClient);
        this.zzguc = zzbly;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(this.zzguc, (zzbro) null, (String) null, (zzbrm) new zzbto(this));
    }
}
