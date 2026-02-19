package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.tasks.Task;

public class LeaderboardsClient extends zzp {
    private static final zzbo<Leaderboards.LeaderboardMetadataResult, LeaderboardBuffer> zzhsq = new zzal();
    private static final zzbo<Leaderboards.LeaderboardMetadataResult, Leaderboard> zzhsr = new zzam();
    private static final zzo<Leaderboards.LeaderboardMetadataResult> zzhss = new zzan();
    private static final zzbo<Leaderboards.LoadPlayerScoreResult, LeaderboardScore> zzhst = new zzac();
    private static final com.google.android.gms.games.internal.zzp zzhsu = new zzad();
    private static final zzbo<Leaderboards.SubmitScoreResult, ScoreSubmissionData> zzhsv = new zzae();
    private static final zzbo<Leaderboards.LoadScoresResult, LeaderboardScores> zzhsw = new zzaf();

    public static class LeaderboardScores implements Releasable {
        private final Leaderboard zzhtc;
        private final LeaderboardScoreBuffer zzhtd;

        LeaderboardScores(Leaderboard leaderboard, LeaderboardScoreBuffer leaderboardScoreBuffer) {
            this.zzhtc = leaderboard;
            this.zzhtd = leaderboardScoreBuffer;
        }

        public Leaderboard getLeaderboard() {
            return this.zzhtc;
        }

        public LeaderboardScoreBuffer getScores() {
            return this.zzhtd;
        }

        public void release() {
            LeaderboardScoreBuffer leaderboardScoreBuffer = this.zzhtd;
            if (leaderboardScoreBuffer != null) {
                leaderboardScoreBuffer.release();
            }
        }
    }

    LeaderboardsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    LeaderboardsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getAllLeaderboardsIntent() {
        return zza(new zzab(this));
    }

    public Task<Intent> getLeaderboardIntent(String str) {
        return zza(new zzag(this, str));
    }

    public Task<Intent> getLeaderboardIntent(String str, int i) {
        return zza(new zzah(this, str, i));
    }

    public Task<Intent> getLeaderboardIntent(String str, int i, int i2) {
        return zza(new zzai(this, str, i, i2));
    }

    public Task<AnnotatedData<LeaderboardScore>> loadCurrentPlayerLeaderboardScore(String str, int i, int i2) {
        return zzg.zzb(Games.Leaderboards.loadCurrentPlayerLeaderboardScore(zzahw(), str, i, i2), zzhst);
    }

    public Task<AnnotatedData<Leaderboard>> loadLeaderboardMetadata(String str, boolean z) {
        return zzg.zza(Games.Leaderboards.loadLeaderboardMetadata(zzahw(), str, z), zzhsr, zzhss);
    }

    public Task<AnnotatedData<LeaderboardBuffer>> loadLeaderboardMetadata(boolean z) {
        return zzg.zzc(Games.Leaderboards.loadLeaderboardMetadata(zzahw(), z), zzhsq);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadMoreScores(LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        return zzg.zzc(Games.Leaderboards.loadMoreScores(zzahw(), leaderboardScoreBuffer, i, i2), zzhsw);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(String str, int i, int i2, int i3) {
        return zzg.zzc(Games.Leaderboards.loadPlayerCenteredScores(zzahw(), str, i, i2, i3), zzhsw);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(String str, int i, int i2, int i3, boolean z) {
        return zzg.zzc(Games.Leaderboards.loadPlayerCenteredScores(zzahw(), str, i, i2, i3, z), zzhsw);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(String str, int i, int i2, int i3) {
        return zzg.zzc(Games.Leaderboards.loadTopScores(zzahw(), str, i, i2, i3), zzhsw);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(String str, int i, int i2, int i3, boolean z) {
        return zzg.zzc(Games.Leaderboards.loadTopScores(zzahw(), str, i, i2, i3, z), zzhsw);
    }

    public void submitScore(String str, long j) {
        zzb(new zzaj(this, str, j));
    }

    public void submitScore(String str, long j, String str2) {
        zzb(new zzak(this, str, j, str2));
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(String str, long j) {
        return zzg.zza(Games.Leaderboards.submitScoreImmediate(zzahw(), str, j), zzhsu, zzhsv);
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(String str, long j, String str2) {
        return zzg.zza(Games.Leaderboards.submitScoreImmediate(zzahw(), str, j, str2), zzhsu, zzhsv);
    }
}
