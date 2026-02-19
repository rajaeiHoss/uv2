package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;

final /* synthetic */ class zzat implements zzbd {
    private final int zzdix;

    zzat(int i) {
        this.zzdix = i;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        zzah.zzet(this.zzdix);
    }
}
