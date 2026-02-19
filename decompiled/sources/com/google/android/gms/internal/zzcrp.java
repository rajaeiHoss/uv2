package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;

final class zzcrp extends zzcru {
    private /* synthetic */ String zzjyt;
    private /* synthetic */ zzci zzjzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrp(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, zzci zzci) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
        this.zzjzg = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza(this, this.zzjyt, this.zzjzg);
    }
}
