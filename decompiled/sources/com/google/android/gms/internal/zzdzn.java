package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzo;

final class zzdzn extends zzebh<ProviderQueryResult, com.google.firebase.auth.internal.zza> {
    private final String zzemh;

    public zzdzn(String str) {
        super(3);
        this.zzemh = zzbq.zzh(str, "email cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzc(this.zzemh, this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(new zzo(this.zzmrt));
    }
}
