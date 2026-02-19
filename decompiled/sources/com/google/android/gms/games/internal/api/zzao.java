package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzao extends zzav {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ long zzhta;
    private /* synthetic */ String zzhtb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzao(zzah zzah, GoogleApiClient googleApiClient, String str, long j, String str2) {
        super(googleApiClient);
        this.zzhsx = str;
        this.zzhta = j;
        this.zzhtb = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Leaderboards.SubmitScoreResult>) this, this.zzhsx, this.zzhta, this.zzhtb);
    }
}
