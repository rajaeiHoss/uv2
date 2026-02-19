package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzccs;
import java.util.HashMap;

public final class ScoreSubmissionData {
    private static final String[] zzids = {"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
    private int zzcc;
    private String zzfld;
    private String zzidu;
    private HashMap<Integer, Result> zziez = new HashMap<>();

    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long j, String str, String str2, boolean z) {
            this.rawScore = j;
            this.formattedScore = str;
            this.scoreTag = str2;
            this.newBest = z;
        }

        public final String toString() {
            return zzbg.zzx(this).zzg("RawScore", Long.valueOf(this.rawScore)).zzg("FormattedScore", this.formattedScore).zzg("ScoreTag", this.scoreTag).zzg("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.zzcc = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        zzbq.checkArgument(count == 3);
        for (int i = 0; i < count; i++) {
            int zzby = dataHolder.zzby(i);
            if (i == 0) {
                this.zzidu = dataHolder.zzd("leaderboardId", i, zzby);
                this.zzfld = dataHolder.zzd("playerId", i, zzby);
            }
            if (dataHolder.zze("hasResult", i, zzby)) {
                this.zziez.put(Integer.valueOf(dataHolder.zzc("timeSpan", i, zzby)), new Result(dataHolder.zzb("rawScore", i, zzby), dataHolder.zzd("formattedScore", i, zzby), dataHolder.zzd("scoreTag", i, zzby), dataHolder.zze("newBest", i, zzby)));
            }
        }
    }

    public final String getLeaderboardId() {
        return this.zzidu;
    }

    public final String getPlayerId() {
        return this.zzfld;
    }

    public final Result getScoreResult(int i) {
        return this.zziez.get(Integer.valueOf(i));
    }

    public final String toString() {
        zzbi zzg = zzbg.zzx(this).zzg("PlayerId", this.zzfld).zzg("StatusCode", Integer.valueOf(this.zzcc));
        for (int i = 0; i < 3; i++) {
            Result result = this.zziez.get(Integer.valueOf(i));
            zzg.zzg("TimesSpan", zzccs.zzdw(i));
            zzg.zzg("Result", result == null ? "null" : result.toString());
        }
        return zzg.toString();
    }
}
