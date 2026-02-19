package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbg extends zzbh {
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbg(zzaz zzaz, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient);
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Players.LoadPlayersResult>) this, this.zziah);
    }
}
