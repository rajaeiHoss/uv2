package com.google.android.gms.internal;

import android.os.RemoteException;

final class zztn implements zzuk {
    zztn(zztm zztm) {
    }

    public final void zzb(zzul zzul) throws RemoteException {
        if (zzul.zzapd != null) {
            zzul.zzapd.onAdClosed();
        }
    }
}
