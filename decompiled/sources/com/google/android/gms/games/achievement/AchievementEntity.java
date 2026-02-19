package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class AchievementEntity extends zzc implements Achievement {
    public static final Parcelable.Creator<AchievementEntity> CREATOR = new zza();
    private final String mName;
    private final int mState;
    private final String zzdxh;
    private final int zzenu;
    private final String zzhwd;
    private final Uri zzhwe;
    private final String zzhwf;
    private final Uri zzhwg;
    private final String zzhwh;
    private final int zzhwi;
    private final String zzhwj;
    private final PlayerEntity zzhwk;
    private final int zzhwl;
    private final String zzhwm;
    private final long zzhwn;
    private final long zzhwo;

    public AchievementEntity(Achievement achievement) {
        String achievementId = achievement.getAchievementId();
        this.zzhwd = achievementId;
        this.zzenu = achievement.getType();
        this.mName = achievement.getName();
        String description = achievement.getDescription();
        this.zzdxh = description;
        this.zzhwe = achievement.getUnlockedImageUri();
        this.zzhwf = achievement.getUnlockedImageUrl();
        this.zzhwg = achievement.getRevealedImageUri();
        this.zzhwh = achievement.getRevealedImageUrl();
        this.zzhwk = (PlayerEntity) achievement.getPlayer().freeze();
        this.mState = achievement.getState();
        this.zzhwn = achievement.getLastUpdatedTimestamp();
        this.zzhwo = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.zzhwi = achievement.getTotalSteps();
            this.zzhwj = achievement.getFormattedTotalSteps();
            this.zzhwl = achievement.getCurrentSteps();
            this.zzhwm = achievement.getFormattedCurrentSteps();
        } else {
            this.zzhwi = 0;
            this.zzhwj = null;
            this.zzhwl = 0;
            this.zzhwm = null;
        }
        com.google.android.gms.common.internal.zzc.zzv(achievementId);
        com.google.android.gms.common.internal.zzc.zzv(description);
    }

    AchievementEntity(String str, int i, String str2, String str3, Uri uri, String str4, Uri uri2, String str5, int i2, String str6, PlayerEntity playerEntity, int i3, int i4, String str7, long j, long j2) {
        this.zzhwd = str;
        this.zzenu = i;
        this.mName = str2;
        this.zzdxh = str3;
        this.zzhwe = uri;
        this.zzhwf = str4;
        this.zzhwg = uri2;
        this.zzhwh = str5;
        this.zzhwi = i2;
        this.zzhwj = str6;
        this.zzhwk = playerEntity;
        this.mState = i3;
        this.zzhwl = i4;
        this.zzhwm = str7;
        this.zzhwn = j;
        this.zzhwo = j2;
    }

    static String zza(Achievement achievement) {
        zzbi zzg = zzbg.zzx(achievement).zzg("Id", achievement.getAchievementId()).zzg("Type", Integer.valueOf(achievement.getType())).zzg("Name", achievement.getName()).zzg("Description", achievement.getDescription()).zzg("Player", achievement.getPlayer()).zzg("State", Integer.valueOf(achievement.getState()));
        if (achievement.getType() == 1) {
            zzg.zzg("CurrentSteps", Integer.valueOf(achievement.getCurrentSteps()));
            zzg.zzg("TotalSteps", Integer.valueOf(achievement.getTotalSteps()));
        }
        return zzg.toString();
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (!(obj instanceof Achievement)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Achievement achievement = (Achievement) obj;
        if (getType() == 1) {
            z2 = zzbg.equal(Integer.valueOf(achievement.getCurrentSteps()), Integer.valueOf(getCurrentSteps()));
            z = zzbg.equal(Integer.valueOf(achievement.getTotalSteps()), Integer.valueOf(getTotalSteps()));
        } else {
            z2 = true;
            z = true;
        }
        return zzbg.equal(achievement.getAchievementId(), getAchievementId()) && zzbg.equal(achievement.getName(), getName()) && zzbg.equal(Integer.valueOf(achievement.getType()), Integer.valueOf(getType())) && zzbg.equal(achievement.getDescription(), getDescription()) && zzbg.equal(Long.valueOf(achievement.getXpValue()), Long.valueOf(getXpValue())) && zzbg.equal(Integer.valueOf(achievement.getState()), Integer.valueOf(getState())) && zzbg.equal(Long.valueOf(achievement.getLastUpdatedTimestamp()), Long.valueOf(getLastUpdatedTimestamp())) && zzbg.equal(achievement.getPlayer(), getPlayer()) && z2 && z;
    }

    public final Achievement freeze() {
        return this;
    }

    public final String getAchievementId() {
        return this.zzhwd;
    }

    public final int getCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        return this.zzhwl;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final String getFormattedCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        return this.zzhwm;
    }

    public final void getFormattedCurrentSteps(CharArrayBuffer charArrayBuffer) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        zzh.zzb(this.zzhwm, charArrayBuffer);
    }

    public final String getFormattedTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        return this.zzhwj;
    }

    public final void getFormattedTotalSteps(CharArrayBuffer charArrayBuffer) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        zzh.zzb(this.zzhwj, charArrayBuffer);
    }

    public final long getLastUpdatedTimestamp() {
        return this.zzhwn;
    }

    public final String getName() {
        return this.mName;
    }

    public final void getName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.mName, charArrayBuffer);
    }

    public final Player getPlayer() {
        return this.zzhwk;
    }

    public final Uri getRevealedImageUri() {
        return this.zzhwg;
    }

    public final String getRevealedImageUrl() {
        return this.zzhwh;
    }

    public final int getState() {
        return this.mState;
    }

    public final int getTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        com.google.android.gms.common.internal.zzc.checkState(z);
        return this.zzhwi;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final Uri getUnlockedImageUri() {
        return this.zzhwe;
    }

    public final String getUnlockedImageUrl() {
        return this.zzhwf;
    }

    public final long getXpValue() {
        return this.zzhwo;
    }

    public final int hashCode() {
        int i;
        int i2;
        if (getType() == 1) {
            i2 = getCurrentSteps();
            i = getTotalSteps();
        } else {
            i2 = 0;
            i = 0;
        }
        return Arrays.hashCode(new Object[]{getAchievementId(), getName(), Integer.valueOf(getType()), getDescription(), Long.valueOf(getXpValue()), Integer.valueOf(getState()), Long.valueOf(getLastUpdatedTimestamp()), getPlayer(), Integer.valueOf(i2), Integer.valueOf(i)});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zza(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getAchievementId(), false);
        zzbgo.zzc(parcel, 2, getType());
        zzbgo.zza(parcel, 3, getName(), false);
        zzbgo.zza(parcel, 4, getDescription(), false);
        zzbgo.zza(parcel, 5, (Parcelable) getUnlockedImageUri(), i, false);
        zzbgo.zza(parcel, 6, getUnlockedImageUrl(), false);
        zzbgo.zza(parcel, 7, (Parcelable) getRevealedImageUri(), i, false);
        zzbgo.zza(parcel, 8, getRevealedImageUrl(), false);
        zzbgo.zzc(parcel, 9, this.zzhwi);
        zzbgo.zza(parcel, 10, this.zzhwj, false);
        zzbgo.zza(parcel, 11, (Parcelable) getPlayer(), i, false);
        zzbgo.zzc(parcel, 12, getState());
        zzbgo.zzc(parcel, 13, this.zzhwl);
        zzbgo.zza(parcel, 14, this.zzhwm, false);
        zzbgo.zza(parcel, 15, getLastUpdatedTimestamp());
        zzbgo.zza(parcel, 16, getXpValue());
        zzbgo.zzai(parcel, zze);
    }
}
