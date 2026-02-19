package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzf extends zzm {
    private /* synthetic */ String val$id;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(com.google.android.gms.games.internal.api.zza zza, String str, GoogleApiClient googleApiClient, String str2) {
        super(str, googleApiClient);
        this.val$id = str2;
    }

    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzb((zzn<Achievements.UpdateAchievementResult>) this, this.val$id);
    }
}
