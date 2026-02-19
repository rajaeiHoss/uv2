package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdf extends zzdh {
    private /* synthetic */ String zzhvz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdf(zzcw zzcw, String str, GoogleApiClient googleApiClient, String str2) {
        super(str, googleApiClient);
        this.zzhvz = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzf((zzn<TurnBasedMultiplayer.CancelMatchResult>) this, this.zzhvz);
    }
}
