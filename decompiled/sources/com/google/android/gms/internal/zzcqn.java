package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqn extends zzdo<zzcov, EndpointDiscoveryCallback> {
    zzcqn(zzcpz zzcpz, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzcov zzcov, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzcov.stopDiscovery();
        taskCompletionSource.setResult(true);
    }
}
