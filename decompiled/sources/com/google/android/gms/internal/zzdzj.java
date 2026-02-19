package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzc;

final class zzdzj extends zzebh<ActionCodeResult, com.google.firebase.auth.internal.zza> {
    private final String zzhxo;

    public zzdzj(String str) {
        super(4);
        this.zzhxo = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzg(this.zzhxo, this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(new zzc(this.zzmru));
    }
}
