package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class ExperienceEventEntity extends zzc implements ExperienceEvent {
    public static final Parcelable.Creator<ExperienceEventEntity> CREATOR = new zza();
    private final int zzenu;
    private final Uri zzhra;
    private final String zzhrl;
    private final String zzibw;
    private final GameEntity zzibx;
    private final String zziby;
    private final String zzibz;
    private final long zzica;
    private final long zzicb;
    private final long zzicc;
    private final int zzicd;

    ExperienceEventEntity(String str, GameEntity gameEntity, String str2, String str3, String str4, Uri uri, long j, long j2, long j3, int i, int i2) {
        this.zzibw = str;
        this.zzibx = gameEntity;
        this.zziby = str2;
        this.zzibz = str3;
        this.zzhrl = str4;
        this.zzhra = uri;
        this.zzica = j;
        this.zzicb = j2;
        this.zzicc = j3;
        this.zzenu = i;
        this.zzicd = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ExperienceEvent)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ExperienceEvent experienceEvent = (ExperienceEvent) obj;
        return zzbg.equal(experienceEvent.zzava(), zzava()) && zzbg.equal(experienceEvent.getGame(), getGame()) && zzbg.equal(experienceEvent.zzavb(), zzavb()) && zzbg.equal(experienceEvent.zzavc(), zzavc()) && zzbg.equal(experienceEvent.getIconImageUrl(), getIconImageUrl()) && zzbg.equal(experienceEvent.getIconImageUri(), getIconImageUri()) && zzbg.equal(Long.valueOf(experienceEvent.zzavd()), Long.valueOf(zzavd())) && zzbg.equal(Long.valueOf(experienceEvent.zzave()), Long.valueOf(zzave())) && zzbg.equal(Long.valueOf(experienceEvent.zzavf()), Long.valueOf(zzavf())) && zzbg.equal(Integer.valueOf(experienceEvent.getType()), Integer.valueOf(getType())) && zzbg.equal(Integer.valueOf(experienceEvent.zzavg()), Integer.valueOf(zzavg()));
    }

    public final ExperienceEvent freeze() {
        return this;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final Uri getIconImageUri() {
        return this.zzhra;
    }

    public final String getIconImageUrl() {
        return this.zzhrl;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzava(), getGame(), zzavb(), zzavc(), getIconImageUrl(), getIconImageUri(), Long.valueOf(zzavd()), Long.valueOf(zzave()), Long.valueOf(zzavf()), Integer.valueOf(getType()), Integer.valueOf(zzavg())});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("ExperienceId", zzava()).zzg("Game", getGame()).zzg("DisplayTitle", zzavb()).zzg("DisplayDescription", zzavc()).zzg("IconImageUrl", getIconImageUrl()).zzg("IconImageUri", getIconImageUri()).zzg("CreatedTimestamp", Long.valueOf(zzavd())).zzg("XpEarned", Long.valueOf(zzave())).zzg("CurrentXp", Long.valueOf(zzavf())).zzg("Type", Integer.valueOf(getType())).zzg("NewLevel", Integer.valueOf(zzavg())).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzibw, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzibx, i, false);
        zzbgo.zza(parcel, 3, this.zziby, false);
        zzbgo.zza(parcel, 4, this.zzibz, false);
        zzbgo.zza(parcel, 5, getIconImageUrl(), false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzhra, i, false);
        zzbgo.zza(parcel, 7, this.zzica);
        zzbgo.zza(parcel, 8, this.zzicb);
        zzbgo.zza(parcel, 9, this.zzicc);
        zzbgo.zzc(parcel, 10, this.zzenu);
        zzbgo.zzc(parcel, 11, this.zzicd);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzava() {
        return this.zzibw;
    }

    public final String zzavb() {
        return this.zziby;
    }

    public final String zzavc() {
        return this.zzibz;
    }

    public final long zzavd() {
        return this.zzica;
    }

    public final long zzave() {
        return this.zzicb;
    }

    public final long zzavf() {
        return this.zzicc;
    }

    public final int zzavg() {
        return this.zzicd;
    }
}
