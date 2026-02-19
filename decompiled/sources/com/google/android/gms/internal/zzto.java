package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzto implements zzuk {
    private /* synthetic */ int zzcdn;

    zzto(zztm zztm, int i) {
        this.zzcdn = i;
    }

    public final void zzb(zzul zzul) throws RemoteException {
        if (zzul.zzapd != null) {
            zzul.zzapd.onAdFailedToLoad(this.zzcdn);
        }
    }
}
