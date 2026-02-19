package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzavf extends zzavc {
    private /* synthetic */ zzave zzehb;

    zzavf(zzave zzave) {
        this.zzehb = zzave;
    }

    public final void zzc(Status status) throws RemoteException {
        this.zzehb.setResult(status);
    }
}
