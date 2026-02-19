package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzeai extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    private final String zzeia;

    public zzeai(String str) {
        super(2);
        this.zzeia = zzbq.zzh(str, "token cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzb(this.zzeia, this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
