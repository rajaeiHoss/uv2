package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqr extends zzde<zzcov, Void> {
    private /* synthetic */ zzcpz zzjyo;
    private /* synthetic */ zzcqs zzjyq;

    zzcqr(zzcpz zzcpz, zzcqs zzcqs) {
        this.zzjyo = zzcpz;
        this.zzjyq = zzcqs;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzjyq.zza(zzcov, new zzcqu(this.zzjyo, taskCompletionSource));
    }
}
