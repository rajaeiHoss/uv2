package com.google.android.gms.internal;

import android.os.RemoteException;

final class zznn implements Runnable {
    private /* synthetic */ zznm zzbkl;

    zznn(zznm zznm) {
        this.zzbkl = zznm;
    }

    public final void run() {
        if (this.zzbkl.zzbkk != null) {
            try {
                this.zzbkl.zzbkk.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzaky.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
