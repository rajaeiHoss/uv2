package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

final class zzl implements Achievements.LoadAchievementsResult {
    private /* synthetic */ Status zzetp;

    zzl(zzk zzk, Status status) {
        this.zzetp = status;
    }

    public final AchievementBuffer getAchievements() {
        return new AchievementBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
