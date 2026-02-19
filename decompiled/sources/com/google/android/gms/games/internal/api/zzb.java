package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzb extends zzk {
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzb(com.google.android.gms.games.internal.api.zza zza, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient, (zzb) null);
        this.zziah = z;
    }

    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzc((zzn<Achievements.LoadAchievementsResult>) this, this.zziah);
    }
}
