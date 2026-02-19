package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthProvider;

final class zzeat extends zzebh<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzece zzmrb;

    public zzeat(zzece zzece) {
        super(8);
        this.zzmrb = (zzece) zzbq.checkNotNull(zzece);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmrb, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
    }
}
