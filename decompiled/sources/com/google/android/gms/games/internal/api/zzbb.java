package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbb extends zzbh {
    private /* synthetic */ String zzfkw;
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbb(zzaz zzaz, GoogleApiClient googleApiClient, String str, boolean z) {
        super(googleApiClient);
        this.zzfkw = str;
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Players.LoadPlayersResult>) this, this.zzfkw, this.zziah);
    }
}
