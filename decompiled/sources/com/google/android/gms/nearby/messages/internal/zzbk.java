package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;

final class zzbk extends zzbv {
    private /* synthetic */ zzci zzkdw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbk(zzbi zzbi, GoogleApiClient googleApiClient, zzci zzci) {
        super(googleApiClient);
        this.zzkdw = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zzc(zzbdx(), this.zzkdw);
    }
}
