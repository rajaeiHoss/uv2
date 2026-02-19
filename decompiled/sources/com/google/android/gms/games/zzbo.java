package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbo extends zzac<Void> {
    final /* synthetic */ zzbn zzhuv;

    zzbo(zzbn zzbn) {
        this.zzhuv = zzbn;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzhuv.zzhut.zza(new zzbp(this));
        taskCompletionSource.setResult(null);
    }
}
