package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaus;
import com.google.android.gms.internal.zzaux;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzt extends zzde<zzaux, Void> implements zzn<Status> {
    private TaskCompletionSource<Void> zzejm;

    private zzt() {
    }

    /* synthetic */ zzt(zzr zzr) {
        this();
    }

    public final void setResult(Status status) {
        if (status.isSuccess()) {
            this.zzejm.setResult(null);
        } else {
            this.zzejm.setException(zzab.zzb(status, "User Action indexing error, please try again."));
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaux zzaux, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzejm = taskCompletionSource;
        zza((zzaus) zzaux.zzalw());
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzaus zzaus) throws RemoteException;

    public final void zzu(Status status) {
        zzbq.checkArgument(!status.isSuccess(), "Failed result must not be success.");
        this.zzejm.setException(zzab.zzb(status, status.getStatusMessage()));
    }
}
