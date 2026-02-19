package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.internal.zza;

final class zzdzk extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final String zzhxo;
    private final String zzmqp;

    public zzdzk(String str, String str2) {
        super(4);
        this.zzhxo = zzbq.zzh(str, "code cannot be null or empty");
        this.zzmqp = zzbq.zzh(str2, "new password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzf(this.zzhxo, this.zzmqp, this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(null);
    }
}
