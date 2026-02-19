package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzxj implements Runnable {
    private /* synthetic */ zzxi zzckk;

    zzxj(zzxi zzxi) {
        this.zzckk = zzxi;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdClicked();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClicked.", e);
        }
    }
}
