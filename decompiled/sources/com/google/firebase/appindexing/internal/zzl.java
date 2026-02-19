package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcb;

final class zzl extends zzcb {
    private /* synthetic */ zzk zzmof;

    zzl(zzk zzk) {
        this.zzmof = zzk;
    }

    public final void zzn(Status status) throws RemoteException {
        this.zzmof.zzjys.setResult(status);
    }
}
