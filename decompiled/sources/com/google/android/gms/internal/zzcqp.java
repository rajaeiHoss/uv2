package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqp extends zzcq<zzcov, String> {
    zzcqp(zzcpz zzcpz, zzci zzci) {
        super(zzci);
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzcov zzcov, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(null);
    }
}
