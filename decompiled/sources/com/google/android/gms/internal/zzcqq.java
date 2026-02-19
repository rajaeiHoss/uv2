package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqq extends zzdo<zzcov, String> {
    zzcqq(zzcpz zzcpz, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzcov zzcov, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(true);
    }
}
