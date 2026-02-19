package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzdzl extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    private String zzekn;
    private String zzemh;

    public zzdzl(String str, String str2) {
        super(2);
        this.zzemh = zzbq.zzh(str, "email cannot be null or empty");
        this.zzekn = zzbq.zzh(str2, "password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzc(this.zzemh, this.zzekn, this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
