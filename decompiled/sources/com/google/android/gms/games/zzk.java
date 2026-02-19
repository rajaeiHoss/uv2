package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzk extends Games.zzc {
    private /* synthetic */ String zzhry;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzi) null);
        this.zzhry = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb(this.zzhry, (zzn<Games.GetServerAuthCodeResult>) this);
    }
}
