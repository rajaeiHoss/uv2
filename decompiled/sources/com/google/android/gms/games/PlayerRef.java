package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.internal.player.zza;
import com.google.android.gms.games.internal.player.zzd;
import com.google.android.gms.games.internal.player.zze;

public final class PlayerRef extends zzc implements Player {
    private final PlayerLevelInfo zzhtj;
    private final zze zzhub;
    private final zzd zzhuc;

    public PlayerRef(DataHolder dataHolder, int i) {
        this(dataHolder, i, (String) null);
    }

    public PlayerRef(DataHolder dataHolder, int i, String str) {
        super(dataHolder, i);
        PlayerLevelInfo playerLevelInfo;
        zze zze = new zze(str);
        this.zzhub = zze;
        this.zzhuc = new zzd(dataHolder, i, zze);
        if (!zzgl(zze.zzict) && getLong(zze.zzict) != -1) {
            int integer = getInteger(zze.zzicu);
            int integer2 = getInteger(zze.zzicx);
            PlayerLevel playerLevel = new PlayerLevel(integer, getLong(zze.zzicv), getLong(zze.zzicw));
            playerLevelInfo = new PlayerLevelInfo(getLong(zze.zzict), getLong(zze.zzicz), playerLevel, integer != integer2 ? new PlayerLevel(integer2, getLong(zze.zzicw), getLong(zze.zzicy)) : playerLevel);
        } else {
            playerLevelInfo = null;
        }
        this.zzhtj = playerLevelInfo;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return PlayerEntity.zza(this, obj);
    }

    public final Player freeze() {
        return new PlayerEntity(this);
    }

    public final Uri getBannerImageLandscapeUri() {
        return zzgk(this.zzhub.zzidk);
    }

    public final String getBannerImageLandscapeUrl() {
        return getString(this.zzhub.zzidl);
    }

    public final Uri getBannerImagePortraitUri() {
        return zzgk(this.zzhub.zzidm);
    }

    public final String getBannerImagePortraitUrl() {
        return getString(this.zzhub.zzidn);
    }

    public final String getDisplayName() {
        return getString(this.zzhub.zzicl);
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        zza(this.zzhub.zzicl, charArrayBuffer);
    }

    public final Uri getHiResImageUri() {
        return zzgk(this.zzhub.zzico);
    }

    public final String getHiResImageUrl() {
        return getString(this.zzhub.zzicp);
    }

    public final Uri getIconImageUri() {
        return zzgk(this.zzhub.zzicm);
    }

    public final String getIconImageUrl() {
        return getString(this.zzhub.zzicn);
    }

    public final long getLastPlayedWithTimestamp() {
        if (!zzgj(this.zzhub.zzics) || zzgl(this.zzhub.zzics)) {
            return -1;
        }
        return getLong(this.zzhub.zzics);
    }

    public final PlayerLevelInfo getLevelInfo() {
        return this.zzhtj;
    }

    public final String getName() {
        return getString(this.zzhub.name);
    }

    public final String getPlayerId() {
        return getString(this.zzhub.zzick);
    }

    public final long getRetrievedTimestamp() {
        return getLong(this.zzhub.zzicq);
    }

    public final String getTitle() {
        return getString(this.zzhub.title);
    }

    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        zza(this.zzhub.title, charArrayBuffer);
    }

    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public final int hashCode() {
        return PlayerEntity.zza(this);
    }

    public final boolean isMuted() {
        return getBoolean(this.zzhub.zzidq);
    }

    public final String toString() {
        return PlayerEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((PlayerEntity) ((Player) freeze())).writeToParcel(parcel, i);
    }

    public final String zzasv() {
        return getString(this.zzhub.zzidj);
    }

    public final boolean zzasw() {
        return getBoolean(this.zzhub.zzidi);
    }

    public final int zzasx() {
        return getInteger(this.zzhub.zzicr);
    }

    public final boolean zzasy() {
        return getBoolean(this.zzhub.zzidb);
    }

    public final zza zzasz() {
        if (zzgl(this.zzhub.zzidc)) {
            return null;
        }
        return this.zzhuc;
    }

    public final int zzata() {
        return getInteger(this.zzhub.zzido);
    }

    public final long zzatb() {
        return getLong(this.zzhub.zzidp);
    }
}
