package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqj extends zzde<zzcov, Void> {
    private /* synthetic */ zzcqv zzjyl;

    zzcqj(zzcpz zzcpz, zzcqv zzcqv) {
        this.zzjyl = zzcqv;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzjyl.zzb(zzcov);
        taskCompletionSource.setResult(null);
    }
}
