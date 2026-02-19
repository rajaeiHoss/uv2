package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzcgb extends zzcgj {
    private /* synthetic */ boolean zzitw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgb(zzcfy zzcfy, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient);
        this.zzitw = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zzbo(this.zzitw);
        setResult(Status.zzftq);
    }
}
