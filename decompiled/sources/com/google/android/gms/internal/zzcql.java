package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcql extends zzdo<zzcov, Boolean> {
    zzcql(zzcpz zzcpz, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzcov zzcov, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzcov.stopAdvertising();
        taskCompletionSource.setResult(true);
    }
}
