package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzcwk extends zzcwl {
    private /* synthetic */ String[] zzkik;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcwk(zzcwf zzcwf, GoogleApiClient googleApiClient, String[] strArr) {
        super(googleApiClient, (zzcwg) null);
        this.zzkik = strArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzh zzh) throws RemoteException {
        zzh.zzc(this, this.zzkik);
    }
}
