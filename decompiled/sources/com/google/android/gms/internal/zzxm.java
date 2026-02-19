package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxm implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxm(zzxi zzxi) {
        this.zzckk = zzxi;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdClosed();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }
}
