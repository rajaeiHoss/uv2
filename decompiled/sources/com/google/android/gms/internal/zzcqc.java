package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;

final /* synthetic */ class zzcqc implements zzcqs {
    private final String zzdiu;

    zzcqc(String str) {
        this.zzdiu = str;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zzj(zzn, this.zzdiu);
    }
}
