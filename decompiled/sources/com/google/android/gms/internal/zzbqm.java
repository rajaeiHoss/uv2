package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbqm extends zzbqw {
    private /* synthetic */ boolean zzgwl = false;
    private /* synthetic */ zzbql zzgwm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbqm(zzbql zzbql, GoogleApiClient googleApiClient, boolean z) {
        super(zzbql, googleApiClient, (zzbqm) null);
        this.zzgwm = zzbql;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbrg(this.zzgwm.zzgpe, this.zzgwl), (zzbrm) new zzbqu(this));
    }
}
