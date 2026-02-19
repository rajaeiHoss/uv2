package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzj extends zza {
    private /* synthetic */ zzi zzens;

    zzj(zzi zzi) {
        this.zzens = zzi;
    }

    public final void zzi(Status status) throws RemoteException {
        this.zzens.setResult(status);
    }
}
