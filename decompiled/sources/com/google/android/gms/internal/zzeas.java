package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzc;

final class zzeas extends zzebh<String, com.google.firebase.auth.internal.zza> {
    private final String zzhxo;

    public zzeas(String str) {
        super(4);
        this.zzhxo = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzg(this.zzhxo, this.zzmrh);
    }

    public final void zzbtu() {
        if (new zzc(this.zzmru).getOperation() != 0) {
            zzax(new Status(17499));
        } else {
            zzbh(this.zzmru.getEmail());
        }
    }
}
