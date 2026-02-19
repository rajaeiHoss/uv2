package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzcx extends zzdj {
    private /* synthetic */ TurnBasedMatchConfig zzibh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcx(zzcw zzcw, GoogleApiClient googleApiClient, TurnBasedMatchConfig turnBasedMatchConfig) {
        super(googleApiClient, (zzcx) null);
        this.zzibh = turnBasedMatchConfig;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<TurnBasedMultiplayer.InitiateMatchResult>) this, this.zzibh);
    }
}
