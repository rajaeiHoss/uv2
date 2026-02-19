package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxp implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxp(zzxi zzxi) {
        this.zzckk = zzxi;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdOpened();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }
}
