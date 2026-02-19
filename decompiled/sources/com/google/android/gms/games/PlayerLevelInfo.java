package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class PlayerLevelInfo extends zzc {
    public static final Parcelable.Creator<PlayerLevelInfo> CREATOR = new zzar();
    private final long zzhtx;
    private final long zzhty;
    private final PlayerLevel zzhtz;
    private final PlayerLevel zzhua;

    public PlayerLevelInfo(long j, long j2, PlayerLevel playerLevel, PlayerLevel playerLevel2) {
        zzbq.checkState(j != -1);
        zzbq.checkNotNull(playerLevel);
        zzbq.checkNotNull(playerLevel2);
        this.zzhtx = j;
        this.zzhty = j2;
        this.zzhtz = playerLevel;
        this.zzhua = playerLevel2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return zzbg.equal(Long.valueOf(this.zzhtx), Long.valueOf(playerLevelInfo.zzhtx)) && zzbg.equal(Long.valueOf(this.zzhty), Long.valueOf(playerLevelInfo.zzhty)) && zzbg.equal(this.zzhtz, playerLevelInfo.zzhtz) && zzbg.equal(this.zzhua, playerLevelInfo.zzhua);
    }

    public final PlayerLevel getCurrentLevel() {
        return this.zzhtz;
    }

    public final long getCurrentXpTotal() {
        return this.zzhtx;
    }

    public final long getLastLevelUpTimestamp() {
        return this.zzhty;
    }

    public final PlayerLevel getNextLevel() {
        return this.zzhua;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzhtx), Long.valueOf(this.zzhty), this.zzhtz, this.zzhua});
    }

    public final boolean isMaxLevel() {
        return this.zzhtz.equals(this.zzhua);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getCurrentXpTotal());
        zzbgo.zza(parcel, 2, getLastLevelUpTimestamp());
        zzbgo.zza(parcel, 3, (Parcelable) getCurrentLevel(), i, false);
        zzbgo.zza(parcel, 4, (Parcelable) getNextLevel(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
