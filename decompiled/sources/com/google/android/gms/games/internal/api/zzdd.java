package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdd extends zzdl {
    private /* synthetic */ String zzhvz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdd(zzcw zzcw, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzcx) null);
        this.zzhvz = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zze((zzn<TurnBasedMultiplayer.LeaveMatchResult>) this, this.zzhvz);
    }
}
