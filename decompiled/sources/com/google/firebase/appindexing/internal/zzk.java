package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzk extends zzde<zzd, Status> {
    /* access modifiers changed from: private */
    public TaskCompletionSource<Status> zzjys;

    private zzk() {
    }

    /* synthetic */ zzk(zzg zzg) {
        this();
    }

    /* access modifiers changed from: protected */
    public final void zza(zzd zzd, TaskCompletionSource<Status> taskCompletionSource) throws RemoteException {
        this.zzjys = taskCompletionSource;
        zza((zzu) zzd.zzalw());
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzu zzu) throws RemoteException;

    /* access modifiers changed from: protected */
    public final zzca zzbtg() {
        return new zzl(this);
    }
}
