package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxt implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxt(zzxi zzxi) {
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
