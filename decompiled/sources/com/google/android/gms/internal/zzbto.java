package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

public final class zzbto extends zzbma {
    private final zzn<Status> zzgbf;

    public zzbto(zzn<Status> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(status);
    }

    public final void onSuccess() throws RemoteException {
        this.zzgbf.setResult(Status.zzftq);
    }
}
