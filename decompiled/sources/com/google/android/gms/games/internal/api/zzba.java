package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzba extends zzbh {
    private /* synthetic */ String zzfkw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzaz zzaz, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzfkw = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Players.LoadPlayersResult>) this, this.zzfkw, false);
    }
}
