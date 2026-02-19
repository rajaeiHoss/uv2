package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzeaa extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    private final String zzekn;
    private final String zzemh;

    public zzeaa(String str, String str2) {
        super(2);
        this.zzemh = zzbq.zzh(str, "email cannot be null or empty");
        this.zzekn = zzbq.zzh(str2, "password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzd(this.zzemh, this.zzekn, this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        if (this.zzmri.getUid().equalsIgnoreCase(zzb.getUid())) {
            ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
            zzbh(new zzf(zzb));
            return;
        }
        zzax(new Status(17024));
    }
}
