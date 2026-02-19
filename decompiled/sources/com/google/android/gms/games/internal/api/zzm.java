package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;

abstract class zzm extends Games.zza<Achievements.UpdateAchievementResult> {
    /* access modifiers changed from: private */
    public final String zzbzd;

    public zzm(String str, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzbzd = str;
    }

    public final /* synthetic */ Achievements.UpdateAchievementResult zzb(Status status) {
        return new zzn(this, status);
    }
}
