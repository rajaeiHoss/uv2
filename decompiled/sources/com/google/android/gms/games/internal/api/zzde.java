package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzde extends zzdl {
    private /* synthetic */ String zzhvz;
    private /* synthetic */ String zzibj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzde(zzcw zzcw, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient, (zzcx) null);
        this.zzhvz = str;
        this.zzibj = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<TurnBasedMultiplayer.LeaveMatchResult>) this, this.zzhvz, this.zzibj);
    }
}
