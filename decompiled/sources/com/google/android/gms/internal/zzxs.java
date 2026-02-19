package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;

final class zzxs implements Runnable {
    private /* synthetic */ zzxi zzckk;
    private /* synthetic */ AdRequest.ErrorCode zzckl;

    zzxs(zzxi zzxi, AdRequest.ErrorCode errorCode) {
        this.zzckk = zzxi;
        this.zzckl = errorCode;
    }

    public final void run() {
        try {
            this.zzckk.zzckb.onAdFailedToLoad(zzxu.zza(this.zzckl));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }
}
