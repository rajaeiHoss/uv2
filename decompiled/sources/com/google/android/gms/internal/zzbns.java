package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbns extends zzbnp {
    private /* synthetic */ zzbra zzgud;
    private /* synthetic */ zzbti zzgue;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbns(zzbnq zzbnq, GoogleApiClient googleApiClient, zzbti zzbti, zzbra zzbra) {
        super(googleApiClient);
        this.zzgue = zzbti;
        this.zzgud = zzbra;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(this.zzgue, (zzbro) this.zzgud, (String) null, (zzbrm) new zzbto(this));
    }
}
