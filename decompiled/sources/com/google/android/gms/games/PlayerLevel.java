package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class PlayerLevel extends zzc {
    public static final Parcelable.Creator<PlayerLevel> CREATOR = new zzaq();
    private final int zzhtu;
    private final long zzhtv;
    private final long zzhtw;

    public PlayerLevel(int i, long j, long j2) {
        boolean z = true;
        zzbq.zza(j >= 0, (Object) "Min XP must be positive!");
        zzbq.zza(j2 <= j ? false : z, (Object) "Max XP must be more than min XP!");
        this.zzhtu = i;
        this.zzhtv = j;
        this.zzhtw = j2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return zzbg.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && zzbg.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && zzbg.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
    }

    public final int getLevelNumber() {
        return this.zzhtu;
    }

    public final long getMaxXp() {
        return this.zzhtw;
    }

    public final long getMinXp() {
        return this.zzhtv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzhtu), Long.valueOf(this.zzhtv), Long.valueOf(this.zzhtw)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("LevelNumber", Integer.valueOf(getLevelNumber())).zzg("MinXp", Long.valueOf(getMinXp())).zzg("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getLevelNumber());
        zzbgo.zza(parcel, 2, getMinXp());
        zzbgo.zza(parcel, 3, getMaxXp());
        zzbgo.zzai(parcel, zze);
    }
}
