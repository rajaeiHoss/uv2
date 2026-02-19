package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzaj extends zzap {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaj(zzah zzah, GoogleApiClient googleApiClient, String str, boolean z) {
        super(googleApiClient, (zzai) null);
        this.zzhsx = str;
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Leaderboards.LeaderboardMetadataResult>) this, this.zzhsx, this.zziah);
    }
}
