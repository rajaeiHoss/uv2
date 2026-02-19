package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxl implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxl(zzxi zzxi) {
        this.zzckk = zzxi;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdLoaded();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }
}
