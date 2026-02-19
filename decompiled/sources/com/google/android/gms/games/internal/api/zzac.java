package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzac<TResult> extends zzde<GamesClientImpl, TResult> {
    /* access modifiers changed from: protected */
    public abstract void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<TResult> taskCompletionSource) throws RemoteException;
}
