package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzr<L> extends zzdo<GamesClientImpl, L> {
    protected zzr(zzck<L> zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zzc(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        try {
            zza(gamesClientImpl, taskCompletionSource);
        } catch (SecurityException e) {
            taskCompletionSource.trySetException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;
}
