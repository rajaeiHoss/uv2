package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzeag extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    public zzeag() {
        super(2);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs, true);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
