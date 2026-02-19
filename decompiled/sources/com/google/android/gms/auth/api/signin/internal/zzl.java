package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzl extends zza {
    private /* synthetic */ zzk zzent;

    zzl(zzk zzk) {
        this.zzent = zzk;
    }

    public final void zzj(Status status) throws RemoteException {
        this.zzent.setResult(status);
    }
}
