package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class SnapshotMetadataEntity extends zzc implements SnapshotMetadata {
    public static final Parcelable.Creator<SnapshotMetadataEntity> CREATOR = new zzf();
    private final String zzdxh;
    private final String zzesj;
    private final String zzhxn;
    private final GameEntity zzibx;
    private final Uri zzihp;
    private final PlayerEntity zzihs;
    private final String zziht;
    private final long zzihu;
    private final long zzihv;
    private final float zzihw;
    private final String zzihx;
    private final boolean zzihy;
    private final long zzihz;
    private final String zziia;

    SnapshotMetadataEntity(GameEntity gameEntity, PlayerEntity playerEntity, String str, Uri uri, String str2, String str3, String str4, long j, long j2, float f, String str5, boolean z, long j3, String str6) {
        this.zzibx = gameEntity;
        this.zzihs = playerEntity;
        this.zzhxn = str;
        this.zzihp = uri;
        this.zziht = str2;
        this.zzihw = f;
        this.zzesj = str3;
        this.zzdxh = str4;
        this.zzihu = j;
        this.zzihv = j2;
        this.zzihx = str5;
        this.zzihy = z;
        this.zzihz = j3;
        this.zziia = str6;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.zzibx = new GameEntity(snapshotMetadata.getGame());
        this.zzihs = new PlayerEntity(snapshotMetadata.getOwner());
        this.zzhxn = snapshotMetadata.getSnapshotId();
        this.zzihp = snapshotMetadata.getCoverImageUri();
        this.zziht = snapshotMetadata.getCoverImageUrl();
        this.zzihw = snapshotMetadata.getCoverImageAspectRatio();
        this.zzesj = snapshotMetadata.getTitle();
        this.zzdxh = snapshotMetadata.getDescription();
        this.zzihu = snapshotMetadata.getLastModifiedTimestamp();
        this.zzihv = snapshotMetadata.getPlayedTime();
        this.zzihx = snapshotMetadata.getUniqueName();
        this.zzihy = snapshotMetadata.hasChangePending();
        this.zzihz = snapshotMetadata.getProgressValue();
        this.zziia = snapshotMetadata.getDeviceName();
    }

    static int zza(SnapshotMetadata snapshotMetadata) {
        return Arrays.hashCode(new Object[]{snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName(), Boolean.valueOf(snapshotMetadata.hasChangePending()), Long.valueOf(snapshotMetadata.getProgressValue()), snapshotMetadata.getDeviceName()});
    }

    static boolean zza(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return zzbg.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && zzbg.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && zzbg.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && zzbg.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && zzbg.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && zzbg.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && zzbg.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && zzbg.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && zzbg.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && zzbg.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName()) && zzbg.equal(Boolean.valueOf(snapshotMetadata2.hasChangePending()), Boolean.valueOf(snapshotMetadata.hasChangePending())) && zzbg.equal(Long.valueOf(snapshotMetadata2.getProgressValue()), Long.valueOf(snapshotMetadata.getProgressValue())) && zzbg.equal(snapshotMetadata2.getDeviceName(), snapshotMetadata.getDeviceName());
    }

    static String zzb(SnapshotMetadata snapshotMetadata) {
        return zzbg.zzx(snapshotMetadata).zzg("Game", snapshotMetadata.getGame()).zzg("Owner", snapshotMetadata.getOwner()).zzg("SnapshotId", snapshotMetadata.getSnapshotId()).zzg("CoverImageUri", snapshotMetadata.getCoverImageUri()).zzg("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).zzg("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).zzg("Description", snapshotMetadata.getDescription()).zzg("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).zzg("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).zzg("UniqueName", snapshotMetadata.getUniqueName()).zzg("ChangePending", Boolean.valueOf(snapshotMetadata.hasChangePending())).zzg("ProgressValue", Long.valueOf(snapshotMetadata.getProgressValue())).zzg("DeviceName", snapshotMetadata.getDeviceName()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final SnapshotMetadata freeze() {
        return this;
    }

    public final float getCoverImageAspectRatio() {
        return this.zzihw;
    }

    public final Uri getCoverImageUri() {
        return this.zzihp;
    }

    public final String getCoverImageUrl() {
        return this.zziht;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final String getDeviceName() {
        return this.zziia;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final long getLastModifiedTimestamp() {
        return this.zzihu;
    }

    public final Player getOwner() {
        return this.zzihs;
    }

    public final long getPlayedTime() {
        return this.zzihv;
    }

    public final long getProgressValue() {
        return this.zzihz;
    }

    public final String getSnapshotId() {
        return this.zzhxn;
    }

    public final String getTitle() {
        return this.zzesj;
    }

    public final String getUniqueName() {
        return this.zzihx;
    }

    public final boolean hasChangePending() {
        return this.zzihy;
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

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getGame(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getOwner(), i, false);
        zzbgo.zza(parcel, 3, getSnapshotId(), false);
        zzbgo.zza(parcel, 5, (Parcelable) getCoverImageUri(), i, false);
        zzbgo.zza(parcel, 6, getCoverImageUrl(), false);
        zzbgo.zza(parcel, 7, this.zzesj, false);
        zzbgo.zza(parcel, 8, getDescription(), false);
        zzbgo.zza(parcel, 9, getLastModifiedTimestamp());
        zzbgo.zza(parcel, 10, getPlayedTime());
        zzbgo.zza(parcel, 11, getCoverImageAspectRatio());
        zzbgo.zza(parcel, 12, getUniqueName(), false);
        zzbgo.zza(parcel, 13, hasChangePending());
        zzbgo.zza(parcel, 14, getProgressValue());
        zzbgo.zza(parcel, 15, getDeviceName(), false);
        zzbgo.zzai(parcel, zze);
    }
}
