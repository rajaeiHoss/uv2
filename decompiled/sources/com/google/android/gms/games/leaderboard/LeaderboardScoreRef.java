package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef extends zzc implements LeaderboardScore {
    private final PlayerRef zziem;

    LeaderboardScoreRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zziem = new PlayerRef(dataHolder, i);
    }

    public final boolean equals(Object obj) {
        return LeaderboardScoreEntity.zza(this, obj);
    }

    public final LeaderboardScore freeze() {
        return new LeaderboardScoreEntity(this);
    }

    public final String getDisplayRank() {
        return getString("display_rank");
    }

    public final void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        zza("display_rank", charArrayBuffer);
    }

    public final String getDisplayScore() {
        return getString("display_score");
    }

    public final void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        zza("display_score", charArrayBuffer);
    }

    public final long getRank() {
        return getLong("rank");
    }

    public final long getRawScore() {
        return getLong("raw_score");
    }

    public final Player getScoreHolder() {
        if (zzgl("external_player_id")) {
            return null;
        }
        return this.zziem;
    }

    public final String getScoreHolderDisplayName() {
        return zzgl("external_player_id") ? getString("default_display_name") : this.zziem.getDisplayName();
    }

    public final void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        if (zzgl("external_player_id")) {
            zza("default_display_name", charArrayBuffer);
        } else {
            this.zziem.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getScoreHolderHiResImageUri() {
        if (zzgl("external_player_id")) {
            return null;
        }
        return this.zziem.getHiResImageUri();
    }

    public final String getScoreHolderHiResImageUrl() {
        if (zzgl("external_player_id")) {
            return null;
        }
        return this.zziem.getHiResImageUrl();
    }

    public final Uri getScoreHolderIconImageUri() {
        return zzgl("external_player_id") ? zzgk("default_display_image_uri") : this.zziem.getIconImageUri();
    }

    public final String getScoreHolderIconImageUrl() {
        return zzgl("external_player_id") ? getString("default_display_image_url") : this.zziem.getIconImageUrl();
    }

    public final String getScoreTag() {
        return getString("score_tag");
    }

    public final long getTimestampMillis() {
        return getLong("achieved_timestamp");
    }

    public final int hashCode() {
        return LeaderboardScoreEntity.zza(this);
    }

    public final String toString() {
        return LeaderboardScoreEntity.zzb(this);
    }
}
