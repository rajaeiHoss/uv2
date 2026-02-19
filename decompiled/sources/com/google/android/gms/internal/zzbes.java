package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbes extends zzbev {
    private /* synthetic */ zzbeq zzfor;
    private /* synthetic */ String zzfos;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbes(zzbeq zzbeq, GoogleApiClient googleApiClient, String str) {
        super(zzbeq, googleApiClient);
        this.zzfor = zzbeq;
        this.zzfos = str;
    }

    public final void zza(zzbfa zzbfa) throws RemoteException {
        zzbfa.zza(new zzbew(this, zzbfa), this.zzfor.zzfoq, this.zzfos);
    }
}
