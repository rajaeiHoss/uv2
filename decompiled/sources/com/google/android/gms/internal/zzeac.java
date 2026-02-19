package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.internal.zza;

final class zzeac extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    public zzeac() {
        super(2);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zze(this.zzmri.zzbtn(), this.zzmrh);
    }

    public final void zzbtu() {
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzdzh.zza(this.zzmpb, this.zzmrs, this.zzmri.isAnonymous()));
        zzbh(null);
    }
}
