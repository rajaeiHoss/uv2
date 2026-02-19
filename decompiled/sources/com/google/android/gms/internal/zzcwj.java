package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;
import java.util.Collection;

final class zzcwj extends zzcwl {
    private /* synthetic */ Collection zzkij;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcwj(zzcwf zzcwf, GoogleApiClient googleApiClient, Collection collection) {
        super(googleApiClient, (zzcwg) null);
        this.zzkij = collection;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzh zzh) throws RemoteException {
        zzh.zza(this, this.zzkij);
    }
}
