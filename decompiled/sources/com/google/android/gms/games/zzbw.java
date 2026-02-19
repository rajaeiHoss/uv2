package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbw extends zzac<Intent> {
    private /* synthetic */ String zzhve;
    private /* synthetic */ boolean zzhvf;
    private /* synthetic */ boolean zzhvg;
    private /* synthetic */ int zzhvh;

    zzbw(SnapshotsClient snapshotsClient, String str, boolean z, boolean z2, int i) {
        this.zzhve = str;
        this.zzhvf = z;
        this.zzhvg = z2;
        this.zzhvh = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zza(this.zzhve, this.zzhvf, this.zzhvg, this.zzhvh));
    }
}
