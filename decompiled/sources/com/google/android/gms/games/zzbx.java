package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbx extends zzac<Void> {
    private /* synthetic */ Snapshot zzhvi;

    zzbx(SnapshotsClient snapshotsClient, Snapshot snapshot) {
        this.zzhvi = snapshot;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zza(this.zzhvi);
        taskCompletionSource.setResult(null);
    }
}
