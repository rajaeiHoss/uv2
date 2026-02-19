package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.internal.zza;

final class zzeao extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final String zzemh;

    public zzeao(String str) {
        super(2);
        this.zzemh = zzbq.zzh(str, "email cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmri.zzbtn(), this.zzemh, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzdzh.zza(this.zzmpb, this.zzmrs));
        zzbh(null);
    }
}
