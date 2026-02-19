package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;

final /* synthetic */ class zzcqf implements zzcqs {
    private final long zzjyj;

    zzcqf(long j) {
        this.zzjyj = j;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zza(zzn, this.zzjyj);
    }
}
