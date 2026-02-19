package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzcxf;
import java.util.List;

final class zzcxh extends zzcxf.zzf {
    private /* synthetic */ String zzkkk = null;
    private /* synthetic */ List zzkkl;
    private /* synthetic */ String zzkkm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcxh(zzcxf zzcxf, GoogleApiClient googleApiClient, List list, String str, String str2) {
        super(googleApiClient);
        this.zzkkl = list;
        this.zzkkm = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxs zzcxs) throws RemoteException {
        zzcxs.zza(this.zzkkr, this.zzkkl, 1, this.zzkkm, this.zzkkk);
    }
}
