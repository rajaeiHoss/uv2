package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;

final /* synthetic */ class zzcqb implements zzcqs {
    private final String zzdiu;
    private final zzci zzjyg;

    zzcqb(String str, zzci zzci) {
        this.zzdiu = str;
        this.zzjyg = zzci;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zza(zzn, this.zzdiu, this.zzjyg);
    }
}
