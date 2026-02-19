package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbe extends zzbh {
    private /* synthetic */ boolean zziah;
    private /* synthetic */ int zziar;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(zzaz zzaz, GoogleApiClient googleApiClient, int i, boolean z) {
        super(googleApiClient);
        this.zziar = i;
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Players.LoadPlayersResult>) this, "played_with", this.zziar, false, this.zziah);
    }
}
