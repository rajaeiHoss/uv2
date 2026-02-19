package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzcwh extends zzcwl {
    private /* synthetic */ String zzkii;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcwh(zzcwf zzcwf, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzcwg) null);
        this.zzkii = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzh zzh) throws RemoteException {
        zza(zzh.zza(this, 0, this.zzkii));
    }
}
