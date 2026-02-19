package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzdo<A extends Api.zzb, L> {
    private final zzck<L> zzgau;

    protected zzdo(zzck<L> zzck) {
        this.zzgau = zzck;
    }

    public final zzck<L> zzakx() {
        return this.zzgau;
    }

    /* access modifiers changed from: protected */
    public abstract void zzc(A a, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;
}
