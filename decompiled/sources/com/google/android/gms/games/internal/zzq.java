package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzq<L> extends zzcq<GamesClientImpl, L> {
    protected zzq(zzci<L> zzci) {
        super(zzci);
    }

    /* access modifiers changed from: protected */
    public final void zzb(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        try {
            zza(gamesClientImpl, taskCompletionSource);
        } catch (SecurityException e) {
            taskCompletionSource.trySetException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;
}
