package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzf extends zzac<Void> {
    private /* synthetic */ String zzhqv;
    private /* synthetic */ int zzhqw;

    zzf(EventsClient eventsClient, String str, int i) {
        this.zzhqv = str;
        this.zzhqw = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzp(this.zzhqv, this.zzhqw);
    }
}
