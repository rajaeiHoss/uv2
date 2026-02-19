package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.achievement.Achievements;

final class zzd implements zzbo<Achievements.UpdateAchievementResult, Boolean> {
    zzd() {
    }

    public final Boolean zzb(Achievements.UpdateAchievementResult result) {
        return Boolean.valueOf(result != null && result.getStatus().getStatusCode() == 3003);
    }
}
