package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcx extends zzac<Boolean> {
    zzcx(VideosClient videosClient) {
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(Boolean.valueOf(gamesClientImpl.zzaun()));
    }
}
