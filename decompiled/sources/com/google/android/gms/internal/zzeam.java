package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzeam extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    public zzeam() {
        super(2);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzd(this.zzmri.zzbtn(), this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
