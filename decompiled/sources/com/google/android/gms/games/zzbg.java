package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.internal.zzs;
import com.google.android.gms.games.internal.zzw;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbg extends zzac<String> {
    private /* synthetic */ String zzhul;

    zzbg(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzhul = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<String> taskCompletionSource) throws RemoteException {
        ((zzw) gamesClientImpl.zzalw()).zza((zzs) new zzbh(this, taskCompletionSource), this.zzhul);
    }
}
