package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider;

final class zzebp implements Runnable {
    private /* synthetic */ zzebq zzmse;
    private /* synthetic */ zzebk zzmsf;

    zzebp(zzebk zzebk, zzebq zzebq) {
        this.zzmsf = zzebk;
        this.zzmse = zzebq;
    }

    public final void run() {
        synchronized (this.zzmsf.zzmsb.zzmrn) {
            if (!this.zzmsf.zzmsb.zzmrn.isEmpty()) {
                this.zzmse.zza((PhoneAuthProvider.OnVerificationStateChangedCallbacks) this.zzmsf.zzmsb.zzmrn.get(0), new Object[0]);
            }
        }
    }
}
