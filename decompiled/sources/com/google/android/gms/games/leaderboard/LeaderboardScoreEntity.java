package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.Arrays;

public final class LeaderboardScoreEntity implements LeaderboardScore {
    private final long zziea;
    private final String zzieb;
    private final String zziec;
    private final long zzied;
    private final long zziee;
    private final String zzief;
    private final Uri zzieg;
    private final Uri zzieh;
    private final PlayerEntity zziei;
    private final String zziej;
    private final String zziek;
    private final String zziel;

    public LeaderboardScoreEntity(LeaderboardScore leaderboardScore) {
        this.zziea = leaderboardScore.getRank();
        this.zzieb = (String) zzbq.checkNotNull(leaderboardScore.getDisplayRank());
        this.zziec = (String) zzbq.checkNotNull(leaderboardScore.getDisplayScore());
        this.zzied = leaderboardScore.getRawScore();
        this.zziee = leaderboardScore.getTimestampMillis();
        this.zzief = leaderboardScore.getScoreHolderDisplayName();
        this.zzieg = leaderboardScore.getScoreHolderIconImageUri();
        this.zzieh = leaderboardScore.getScoreHolderHiResImageUri();
        Player scoreHolder = leaderboardScore.getScoreHolder();
        this.zziei = scoreHolder == null ? null : (PlayerEntity) scoreHolder.freeze();
        this.zziej = leaderboardScore.getScoreTag();
        this.zziek = leaderboardScore.getScoreHolderIconImageUrl();
        this.zziel = leaderboardScore.getScoreHolderHiResImageUrl();
    }

    static int zza(LeaderboardScore leaderboardScore) {
        return Arrays.hashCode(new Object[]{Long.valueOf(leaderboardScore.getRank()), leaderboardScore.getDisplayRank(), Long.valueOf(leaderboardScore.getRawScore()), leaderboardScore.getDisplayScore(), Long.valueOf(leaderboardScore.getTimestampMillis()), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder()});
    }

    static boolean zza(LeaderboardScore leaderboardScore, Object obj) {
        if (!(obj instanceof LeaderboardScore)) {
            return false;
        }
        if (leaderboardScore == obj) {
            return true;
        }
        LeaderboardScore leaderboardScore2 = (LeaderboardScore) obj;
        return zzbg.equal(Long.valueOf(leaderboardScore2.getRank()), Long.valueOf(leaderboardScore.getRank())) && zzbg.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && zzbg.equal(Long.valueOf(leaderboardScore2.getRawScore()), Long.valueOf(leaderboardScore.getRawScore())) && zzbg.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && zzbg.equal(Long.valueOf(leaderboardScore2.getTimestampMillis()), Long.valueOf(leaderboardScore.getTimestampMillis())) && zzbg.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && zzbg.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && zzbg.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && zzbg.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder()) && zzbg.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag());
    }

    static String zzb(LeaderboardScore leaderboardScore) {
        return zzbg.zzx(leaderboardScore).zzg("Rank", Long.valueOf(leaderboardScore.getRank())).zzg("DisplayRank", leaderboardScore.getDisplayRank()).zzg("Score", Long.valueOf(leaderboardScore.getRawScore())).zzg("DisplayScore", leaderboardScore.getDisplayScore()).zzg("Timestamp", Long.valueOf(leaderboardScore.getTimestampMillis())).zzg("DisplayName", leaderboardScore.getScoreHolderDisplayName()).zzg("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).zzg("IconImageUrl", leaderboardScore.getScoreHolderIconImageUrl()).zzg("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri()).zzg("HiResImageUrl", leaderboardScore.getScoreHolderHiResImageUrl()).zzg("Player", leaderboardScore.getScoreHolder() == null ? null : leaderboardScore.getScoreHolder()).zzg("ScoreTag", leaderboardScore.getScoreTag()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final LeaderboardScore freeze() {
        return this;
    }

    public final String getDisplayRank() {
        return this.zzieb;
    }

    public final void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzieb, charArrayBuffer);
    }

    public final String getDisplayScore() {
        return this.zziec;
    }

    public final void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zziec, charArrayBuffer);
    }

    public final long getRank() {
        return this.zziea;
    }

    public final long getRawScore() {
        return this.zzied;
    }

    public final Player getScoreHolder() {
        return this.zziei;
    }

    public final String getScoreHolderDisplayName() {
        PlayerEntity playerEntity = this.zziei;
        return playerEntity == null ? this.zzief : playerEntity.getDisplayName();
    }

    public final void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        PlayerEntity playerEntity = this.zziei;
        if (playerEntity == null) {
            zzh.zzb(this.zzief, charArrayBuffer);
        } else {
            playerEntity.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getScoreHolderHiResImageUri() {
        PlayerEntity playerEntity = this.zziei;
        return playerEntity == null ? this.zzieh : playerEntity.getHiResImageUri();
    }

    public final String getScoreHolderHiResImageUrl() {
        PlayerEntity playerEntity = this.zziei;
        return playerEntity == null ? this.zziel : playerEntity.getHiResImageUrl();
    }

    public final Uri getScoreHolderIconImageUri() {
        PlayerEntity playerEntity = this.zziei;
        return playerEntity == null ? this.zzieg : playerEntity.getIconImageUri();
    }

    public final String getScoreHolderIconImageUrl() {
        PlayerEntity playerEntity = this.zziei;
        return playerEntity == null ? this.zziek : playerEntity.getIconImageUrl();
    }

    public final String getScoreTag() {
        return this.zziej;
    }

    public final long getTimestampMillis() {
        return this.zziee;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }
}
