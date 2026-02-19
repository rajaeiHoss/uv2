package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzavh extends zzavc {
    private /* synthetic */ zzavg zzehc;

    zzavh(zzavg zzavg) {
        this.zzehc = zzavg;
    }

    public final void zzc(Status status) throws RemoteException {
        this.zzehc.setResult(status);
    }
}
