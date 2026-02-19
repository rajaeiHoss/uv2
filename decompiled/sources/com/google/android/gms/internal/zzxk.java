package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxk implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxk(zzxi zzxi) {
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
