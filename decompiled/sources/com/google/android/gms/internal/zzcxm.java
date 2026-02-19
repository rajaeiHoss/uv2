package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzcxf;

final class zzcxm extends zzcxf.zze {
    private /* synthetic */ String zzkkp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcxm(zzcxf zzcxf, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzkkp = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxs zzcxs) throws RemoteException {
        ((zzcxd) zzcxs.zzalw()).zza(this.zzkkr, this.zzkkp);
    }
}
