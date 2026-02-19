package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzean extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    private String zzmqz;

    public zzean(String str) {
        super(2);
        this.zzmqz = zzbq.zzh(str, "provider cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zze(this.zzmqz, this.zzmri.zzbtn(), this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
