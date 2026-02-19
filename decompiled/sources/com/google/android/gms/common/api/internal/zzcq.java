package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzcq<A extends Api.zzb, L> {
    private final zzci<L> zzgbb;

    protected zzcq(zzci<L> zzci) {
        this.zzgbb = zzci;
    }

    public final zzck<L> zzakx() {
        return this.zzgbb.zzakx();
    }

    public final void zzaky() {
        this.zzgbb.clear();
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;
}
