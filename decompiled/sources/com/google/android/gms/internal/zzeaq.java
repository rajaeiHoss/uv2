package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zza;

final class zzeaq extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final PhoneAuthCredential zzmqt;

    public zzeaq(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzmqt = (PhoneAuthCredential) zzbq.checkNotNull(phoneAuthCredential);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmri.zzbtn(), this.zzmqt, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzdzh.zza(this.zzmpb, this.zzmrs));
        zzbh(null);
    }
}
