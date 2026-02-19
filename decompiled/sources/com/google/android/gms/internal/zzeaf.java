package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.internal.zza;

final class zzeaf extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private String zzmqx;

    public zzeaf(String str) {
        super(9);
        this.zzmqx = str;
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzi(this.zzmqx, this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(null);
    }
}
