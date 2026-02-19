package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.tasks.Task;

public class AchievementsClient extends zzp {
    private static final zzbo<Achievements.LoadAchievementsResult, AchievementBuffer> zzhqp = new zzb();
    private static final zzbo<Achievements.UpdateAchievementResult, Void> zzhqq = new zzc();
    private static final zzbo<Achievements.UpdateAchievementResult, Boolean> zzhqr = new zzd();
    private static final com.google.android.gms.games.internal.zzp zzhqs = new zze();

    AchievementsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    AchievementsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    private static Task<Void> zzc(PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return zzg.zza(pendingResult, zzhqs, zzhqq);
    }

    private static Task<Boolean> zzd(PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return zzg.zza(pendingResult, zzhqs, zzhqr);
    }

    public Task<Intent> getAchievementsIntent() {
        return zza(new com.google.android.gms.games.zza(this));
    }

    public void increment(String str, int i) {
        Games.Achievements.increment(zzahw(), str, i);
    }

    public Task<Boolean> incrementImmediate(String str, int i) {
        return zzd(Games.Achievements.incrementImmediate(zzahw(), str, i));
    }

    public Task<AnnotatedData<AchievementBuffer>> load(boolean z) {
        return zzg.zzc(Games.Achievements.load(zzahw(), z), zzhqp);
    }

    public void reveal(String str) {
        Games.Achievements.reveal(zzahw(), str);
    }

    public Task<Void> revealImmediate(String str) {
        return zzc(Games.Achievements.revealImmediate(zzahw(), str));
    }

    public void setSteps(String str, int i) {
        Games.Achievements.setSteps(zzahw(), str, i);
    }

    public Task<Boolean> setStepsImmediate(String str, int i) {
        return zzd(Games.Achievements.setStepsImmediate(zzahw(), str, i));
    }

    public void unlock(String str) {
        Games.Achievements.unlock(zzahw(), str);
    }

    public Task<Void> unlockImmediate(String str) {
        return zzc(Games.Achievements.unlockImmediate(zzahw(), str));
    }
}
