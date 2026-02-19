package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxo implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxo(zzxi zzxi) {
        this.zzckk = zzxi;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdLeftApplication();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLeftApplication.", e);
        }
    }
}
