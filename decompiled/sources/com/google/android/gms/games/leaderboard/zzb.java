package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzccs;
import java.util.Arrays;
import org.jivesoftware.smack.packet.PrivacyItem;

public final class zzb implements LeaderboardVariant {
    private final int zzien;
    private final int zzieo;
    private final boolean zziep;
    private final long zzieq;
    private final String zzier;
    private final long zzies;
    private final String zziet;
    private final String zzieu;
    private final long zziev;
    private final String zziew;
    private final String zziex;
    private final String zziey;

    public zzb(LeaderboardVariant leaderboardVariant) {
        this.zzien = leaderboardVariant.getTimeSpan();
        this.zzieo = leaderboardVariant.getCollection();
        this.zziep = leaderboardVariant.hasPlayerInfo();
        this.zzieq = leaderboardVariant.getRawPlayerScore();
        this.zzier = leaderboardVariant.getDisplayPlayerScore();
        this.zzies = leaderboardVariant.getPlayerRank();
        this.zziet = leaderboardVariant.getDisplayPlayerRank();
        this.zzieu = leaderboardVariant.getPlayerScoreTag();
        this.zziev = leaderboardVariant.getNumScores();
        this.zziew = leaderboardVariant.zzavq();
        this.zziex = leaderboardVariant.zzavr();
        this.zziey = leaderboardVariant.zzavs();
    }

    static int zza(LeaderboardVariant leaderboardVariant) {
        return Arrays.hashCode(new Object[]{Integer.valueOf(leaderboardVariant.getTimeSpan()), Integer.valueOf(leaderboardVariant.getCollection()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo()), Long.valueOf(leaderboardVariant.getRawPlayerScore()), leaderboardVariant.getDisplayPlayerScore(), Long.valueOf(leaderboardVariant.getPlayerRank()), leaderboardVariant.getDisplayPlayerRank(), Long.valueOf(leaderboardVariant.getNumScores()), leaderboardVariant.zzavq(), leaderboardVariant.zzavs(), leaderboardVariant.zzavr()});
    }

    static boolean zza(LeaderboardVariant leaderboardVariant, Object obj) {
        if (!(obj instanceof LeaderboardVariant)) {
            return false;
        }
        if (leaderboardVariant == obj) {
            return true;
        }
        LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant) obj;
        return zzbg.equal(Integer.valueOf(leaderboardVariant2.getTimeSpan()), Integer.valueOf(leaderboardVariant.getTimeSpan())) && zzbg.equal(Integer.valueOf(leaderboardVariant2.getCollection()), Integer.valueOf(leaderboardVariant.getCollection())) && zzbg.equal(Boolean.valueOf(leaderboardVariant2.hasPlayerInfo()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo())) && zzbg.equal(Long.valueOf(leaderboardVariant2.getRawPlayerScore()), Long.valueOf(leaderboardVariant.getRawPlayerScore())) && zzbg.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && zzbg.equal(Long.valueOf(leaderboardVariant2.getPlayerRank()), Long.valueOf(leaderboardVariant.getPlayerRank())) && zzbg.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && zzbg.equal(Long.valueOf(leaderboardVariant2.getNumScores()), Long.valueOf(leaderboardVariant.getNumScores())) && zzbg.equal(leaderboardVariant2.zzavq(), leaderboardVariant.zzavq()) && zzbg.equal(leaderboardVariant2.zzavs(), leaderboardVariant.zzavs()) && zzbg.equal(leaderboardVariant2.zzavr(), leaderboardVariant.zzavr());
    }

    static String zzb(LeaderboardVariant leaderboardVariant) {
        String str;
        zzbi zzg = zzbg.zzx(leaderboardVariant).zzg("TimeSpan", zzccs.zzdw(leaderboardVariant.getTimeSpan()));
        int collection = leaderboardVariant.getCollection();
        if (collection == -1) {
            str = "UNKNOWN";
        } else if (collection == 0) {
            str = "PUBLIC";
        } else if (collection == 1) {
            str = "SOCIAL";
        } else if (collection == 2) {
            str = "SOCIAL_1P";
        } else {
            StringBuilder sb = new StringBuilder(43);
            sb.append("Unknown leaderboard collection: ");
            sb.append(collection);
            throw new IllegalArgumentException(sb.toString());
        }
        zzbi zzg2 = zzg.zzg("Collection", str);
        boolean hasPlayerInfo = leaderboardVariant.hasPlayerInfo();
        String str2 = PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE;
        zzbi zzg3 = zzg2.zzg("RawPlayerScore", hasPlayerInfo ? Long.valueOf(leaderboardVariant.getRawPlayerScore()) : str2).zzg("DisplayPlayerScore", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerScore() : str2).zzg("PlayerRank", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getPlayerRank()) : str2);
        if (leaderboardVariant.hasPlayerInfo()) {
            str2 = leaderboardVariant.getDisplayPlayerRank();
        }
        return zzg3.zzg("DisplayPlayerRank", str2).zzg("NumScores", Long.valueOf(leaderboardVariant.getNumScores())).zzg("TopPageNextToken", leaderboardVariant.zzavq()).zzg("WindowPageNextToken", leaderboardVariant.zzavs()).zzg("WindowPagePrevToken", leaderboardVariant.zzavr()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final LeaderboardVariant freeze() {
        return this;
    }

    public final int getCollection() {
        return this.zzieo;
    }

    public final String getDisplayPlayerRank() {
        return this.zziet;
    }

    public final String getDisplayPlayerScore() {
        return this.zzier;
    }

    public final long getNumScores() {
        return this.zziev;
    }

    public final long getPlayerRank() {
        return this.zzies;
    }

    public final String getPlayerScoreTag() {
        return this.zzieu;
    }

    public final long getRawPlayerScore() {
        return this.zzieq;
    }

    public final int getTimeSpan() {
        return this.zzien;
    }

    public final boolean hasPlayerInfo() {
        return this.zziep;
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

    public final String zzavq() {
        return this.zziew;
    }

    public final String zzavr() {
        return this.zziex;
    }

    public final String zzavs() {
        return this.zziey;
    }
}
