package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzcbo extends zzbzo {
    private final zzn<SessionReadResult> zzhmf;

    private zzcbo(zzn<SessionReadResult> zzn) {
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcbo(zzn zzn, zzcbi zzcbi) {
        this(zzn);
    }

    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        this.zzhmf.setResult(sessionReadResult);
    }
}
