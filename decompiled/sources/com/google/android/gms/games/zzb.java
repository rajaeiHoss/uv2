package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

final class zzb implements zzbo<Achievements.LoadAchievementsResult, AchievementBuffer> {
    zzb() {
    }

    public final AchievementBuffer zzb(Achievements.LoadAchievementsResult result) {
        if (result == null) {
            return null;
        }
        return result.getAchievements();
    }
}
