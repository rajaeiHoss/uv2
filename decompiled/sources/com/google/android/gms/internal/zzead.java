package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.internal.zza;

final class zzead extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private String zzeia;
    private ActionCodeSettings zzmqw;

    public zzead(String str, ActionCodeSettings actionCodeSettings) {
        super(6);
        this.zzeia = zzbq.zzh(str, "token cannot be null or empty");
        this.zzmqw = actionCodeSettings;
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzeia, this.zzmqw, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        zzbh(null);
    }
}
