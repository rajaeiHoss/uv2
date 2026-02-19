package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.internal.zza;

final class zzdzi extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final String zzhxo;

    public zzdzi(String str) {
        super(7);
        this.zzhxo = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzh(this.zzhxo, this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(null);
    }
}
