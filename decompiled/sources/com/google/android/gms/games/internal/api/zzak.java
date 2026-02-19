package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzak extends zzar {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ int zziam;
    private /* synthetic */ int zzian;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzak(zzah zzah, GoogleApiClient googleApiClient, String str, int i, int i2) {
        super(googleApiClient, (zzai) null);
        this.zzhsx = str;
        this.zziam = i;
        this.zzian = i2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Leaderboards.LoadPlayerScoreResult>) this, (String) null, this.zzhsx, this.zziam, this.zzian);
    }
}
