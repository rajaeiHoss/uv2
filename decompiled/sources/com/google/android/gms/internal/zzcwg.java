package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzcwg extends zzcwl {
    private /* synthetic */ int zzkih;
    private /* synthetic */ String zzkii;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcwg(zzcwf zzcwf, GoogleApiClient googleApiClient, int i, String str) {
        super(googleApiClient, (zzcwg) null);
        this.zzkih = i;
        this.zzkii = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzh zzh) throws RemoteException {
        zza(zzh.zza(this, this.zzkih, this.zzkii));
    }
}
