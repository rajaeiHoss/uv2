package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.zzal;

final class zzcgp extends zzcgq {
    private /* synthetic */ zzal zziub;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgp(zzcgn zzcgn, GoogleApiClient googleApiClient, zzal zzal) {
        super(googleApiClient);
        this.zziub = zzal;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zziub, (zzn<Status>) this);
    }
}
