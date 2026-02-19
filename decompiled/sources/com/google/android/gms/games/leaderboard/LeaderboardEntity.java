package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;
import java.util.Arrays;

public final class LeaderboardEntity implements Leaderboard {
    private final String zzemi;
    private final Uri zzhra;
    private final String zzhrl;
    private final String zzidu;
    private final int zzidv;
    private final ArrayList<zzb> zzidw;
    private final Game zzidx;

    public LeaderboardEntity(Leaderboard leaderboard) {
        this.zzidu = leaderboard.getLeaderboardId();
        this.zzemi = leaderboard.getDisplayName();
        this.zzhra = leaderboard.getIconImageUri();
        this.zzhrl = leaderboard.getIconImageUrl();
        this.zzidv = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        this.zzidx = game == null ? null : new GameEntity(game);
        ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        int size = variants.size();
        this.zzidw = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.zzidw.add((zzb) variants.get(i).freeze());
        }
    }

    static int zza(Leaderboard leaderboard) {
        return Arrays.hashCode(new Object[]{leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), Integer.valueOf(leaderboard.getScoreOrder()), leaderboard.getVariants()});
    }

    static boolean zza(Leaderboard leaderboard, Object obj) {
        if (!(obj instanceof Leaderboard)) {
            return false;
        }
        if (leaderboard == obj) {
            return true;
        }
        Leaderboard leaderboard2 = (Leaderboard) obj;
        return zzbg.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && zzbg.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && zzbg.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && zzbg.equal(Integer.valueOf(leaderboard2.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && zzbg.equal(leaderboard2.getVariants(), leaderboard.getVariants());
    }

    static String zzb(Leaderboard leaderboard) {
        return zzbg.zzx(leaderboard).zzg("LeaderboardId", leaderboard.getLeaderboardId()).zzg("DisplayName", leaderboard.getDisplayName()).zzg("IconImageUri", leaderboard.getIconImageUri()).zzg("IconImageUrl", leaderboard.getIconImageUrl()).zzg("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).zzg("Variants", leaderboard.getVariants()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Leaderboard freeze() {
        return this;
    }

    public final String getDisplayName() {
        return this.zzemi;
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzemi, charArrayBuffer);
    }

    public final Game getGame() {
        return this.zzidx;
    }

    public final Uri getIconImageUri() {
        return this.zzhra;
    }

    public final String getIconImageUrl() {
        return this.zzhrl;
    }

    public final String getLeaderboardId() {
        return this.zzidu;
    }

    public final int getScoreOrder() {
        return this.zzidv;
    }

    public final ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<>(this.zzidw);
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
