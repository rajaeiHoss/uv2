package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzayf extends zzde<zzayb, Void> {
    private TaskCompletionSource<Void> zzejm;

    private zzayf() {
    }

    /* synthetic */ zzayf(zzayd zzayd) {
        this();
    }

    /* access modifiers changed from: protected */
    public final void zza(zzayb zzayb, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzejm = taskCompletionSource;
        zza((zzaxx) zzayb.zzalw());
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzaxx zzaxx) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zzh(Status status) {
        zzdf.zza(status, null, this.zzejm);
    }
}
