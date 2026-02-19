package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.internal.zza;

final class zzeae extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final String zzemh;
    private final ActionCodeSettings zzmqw;

    public zzeae(String str, ActionCodeSettings actionCodeSettings) {
        super(4);
        this.zzemh = zzbq.zzh(str, "email cannot be null or empty");
        this.zzmqw = actionCodeSettings;
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zzb(this.zzemh, this.zzmqw, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(null);
    }
}
