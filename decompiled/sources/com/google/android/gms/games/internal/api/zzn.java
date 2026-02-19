package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements;

final class zzn implements Achievements.UpdateAchievementResult {
    private /* synthetic */ Status zzetp;
    private /* synthetic */ zzm zziaj;

    zzn(zzm zzm, Status status) {
        this.zziaj = zzm;
        this.zzetp = status;
    }

    public final String getAchievementId() {
        return this.zziaj.zzbzd;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
