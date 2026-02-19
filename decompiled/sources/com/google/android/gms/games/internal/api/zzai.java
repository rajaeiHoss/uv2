package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzai extends zzap {
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzai(zzah zzah, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient, (zzai) null);
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Leaderboards.LeaderboardMetadataResult>) this, this.zziah);
    }
}
