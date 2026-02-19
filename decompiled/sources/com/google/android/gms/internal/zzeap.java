package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.internal.zza;

final class zzeap extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final String zzekn;

    public zzeap(String str) {
        super(2);
        this.zzekn = zzbq.zzh(str, "password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzb(this.zzmri.zzbtn(), this.zzekn, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzdzh.zza(this.zzmpb, this.zzmrs));
        zzbh(null);
    }
}
