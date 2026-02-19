package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbnr extends zzbnp {
    private /* synthetic */ zzbly zzguc;
    private /* synthetic */ zzbra zzgud;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbnr(zzbnq zzbnq, GoogleApiClient googleApiClient, zzbly zzbly, zzbra zzbra) {
        super(googleApiClient);
        this.zzguc = zzbly;
        this.zzgud = zzbra;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(this.zzguc, (zzbro) this.zzgud, (String) null, (zzbrm) new zzbto(this));
    }
}
