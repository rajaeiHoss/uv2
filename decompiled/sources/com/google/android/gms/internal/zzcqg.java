package com.google.android.gms.internal;

import android.os.RemoteException;
final /* synthetic */ class zzcqg implements zzcqv {
    private final String zzdiu;

    zzcqg(String str) {
        this.zzdiu = str;
    }

    public final void zzb(zzcov zzcov) throws RemoteException {
        zzcov.disconnectFromEndpoint(this.zzdiu);
    }
}
