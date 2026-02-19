package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.zzb;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Parcelable.Creator<PlayerEntity> CREATOR = new zza();
    private final String mName;
    private String zzemi;
    private final String zzesj;
    private String zzfld;
    private final Uri zzhra;
    private final Uri zzhrb;
    private final String zzhrl;
    private final String zzhrm;
    private final long zzhtf;
    private final int zzhtg;
    private final long zzhth;
    private final zzb zzhti;
    private final PlayerLevelInfo zzhtj;
    private final boolean zzhtk;
    private final boolean zzhtl;
    private final String zzhtm;
    private final Uri zzhtn;
    private final String zzhto;
    private final Uri zzhtp;
    private final String zzhtq;
    private final int zzhtr;
    private final long zzhts;
    private final boolean zzhtt;

    static final class zza extends zzap {
        zza() {
        }

        public final PlayerEntity createFromParcel(Parcel parcel) {
            return zzp(parcel);
        }

        public final PlayerEntity zzp(Parcel parcel) {
            if (PlayerEntity.zze(PlayerEntity.zzamq()) || PlayerEntity.zzgq(PlayerEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            return new PlayerEntity(readString, readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), -1, -1, (String) null, (String) null, (String) null, (zzb) null, (PlayerLevelInfo) null, true, false, parcel.readString(), parcel.readString(), (Uri) null, (String) null, (Uri) null, (String) null, -1, -1, false);
        }
    }

    public PlayerEntity(Player player) {
        this(player, true);
    }

    private PlayerEntity(Player player, boolean z) {
        this.zzfld = player.getPlayerId();
        this.zzemi = player.getDisplayName();
        this.zzhra = player.getIconImageUri();
        this.zzhrl = player.getIconImageUrl();
        this.zzhrb = player.getHiResImageUri();
        this.zzhrm = player.getHiResImageUrl();
        long retrievedTimestamp = player.getRetrievedTimestamp();
        this.zzhtf = retrievedTimestamp;
        this.zzhtg = player.zzasx();
        this.zzhth = player.getLastPlayedWithTimestamp();
        this.zzesj = player.getTitle();
        this.zzhtk = player.zzasy();
        com.google.android.gms.games.internal.player.zza zzasz = player.zzasz();
        this.zzhti = zzasz == null ? null : new zzb(zzasz);
        this.zzhtj = player.getLevelInfo();
        this.zzhtl = player.zzasw();
        this.zzhtm = player.zzasv();
        this.mName = player.getName();
        this.zzhtn = player.getBannerImageLandscapeUri();
        this.zzhto = player.getBannerImageLandscapeUrl();
        this.zzhtp = player.getBannerImagePortraitUri();
        this.zzhtq = player.getBannerImagePortraitUrl();
        this.zzhtr = player.zzata();
        this.zzhts = player.zzatb();
        this.zzhtt = player.isMuted();
        zzc.zzv(this.zzfld);
        zzc.zzv(this.zzemi);
        zzc.checkState(retrievedTimestamp > 0);
    }

    PlayerEntity(String str, String str2, Uri uri, Uri uri2, long j, int i, long j2, String str3, String str4, String str5, zzb zzb, PlayerLevelInfo playerLevelInfo, boolean z, boolean z2, String str6, String str7, Uri uri3, String str8, Uri uri4, String str9, int i2, long j3, boolean z3) {
        this.zzfld = str;
        this.zzemi = str2;
        this.zzhra = uri;
        this.zzhrl = str3;
        this.zzhrb = uri2;
        this.zzhrm = str4;
        this.zzhtf = j;
        this.zzhtg = i;
        this.zzhth = j2;
        this.zzesj = str5;
        this.zzhtk = z;
        this.zzhti = zzb;
        this.zzhtj = playerLevelInfo;
        this.zzhtl = z2;
        this.zzhtm = str6;
        this.mName = str7;
        this.zzhtn = uri3;
        this.zzhto = str8;
        this.zzhtp = uri4;
        this.zzhtq = str9;
        this.zzhtr = i2;
        this.zzhts = j3;
        this.zzhtt = z3;
    }

    static int zza(Player player) {
        return Arrays.hashCode(new Object[]{player.getPlayerId(), player.getDisplayName(), Boolean.valueOf(player.zzasw()), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo(), player.zzasv(), player.getName(), player.getBannerImageLandscapeUri(), player.getBannerImagePortraitUri(), Integer.valueOf(player.zzata()), Long.valueOf(player.zzatb()), Boolean.valueOf(player.isMuted())});
    }

    static boolean zza(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return zzbg.equal(player2.getPlayerId(), player.getPlayerId()) && zzbg.equal(player2.getDisplayName(), player.getDisplayName()) && zzbg.equal(Boolean.valueOf(player2.zzasw()), Boolean.valueOf(player.zzasw())) && zzbg.equal(player2.getIconImageUri(), player.getIconImageUri()) && zzbg.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && zzbg.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && zzbg.equal(player2.getTitle(), player.getTitle()) && zzbg.equal(player2.getLevelInfo(), player.getLevelInfo()) && zzbg.equal(player2.zzasv(), player.zzasv()) && zzbg.equal(player2.getName(), player.getName()) && zzbg.equal(player2.getBannerImageLandscapeUri(), player.getBannerImageLandscapeUri()) && zzbg.equal(player2.getBannerImagePortraitUri(), player.getBannerImagePortraitUri()) && zzbg.equal(Integer.valueOf(player2.zzata()), Integer.valueOf(player.zzata())) && zzbg.equal(Long.valueOf(player2.zzatb()), Long.valueOf(player.zzatb())) && zzbg.equal(Boolean.valueOf(player2.isMuted()), Boolean.valueOf(player.isMuted()));
    }

    static String zzb(Player player) {
        return zzbg.zzx(player).zzg("PlayerId", player.getPlayerId()).zzg("DisplayName", player.getDisplayName()).zzg("HasDebugAccess", Boolean.valueOf(player.zzasw())).zzg("IconImageUri", player.getIconImageUri()).zzg("IconImageUrl", player.getIconImageUrl()).zzg("HiResImageUri", player.getHiResImageUri()).zzg("HiResImageUrl", player.getHiResImageUrl()).zzg("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).zzg("Title", player.getTitle()).zzg("LevelInfo", player.getLevelInfo()).zzg("GamerTag", player.zzasv()).zzg("Name", player.getName()).zzg("BannerImageLandscapeUri", player.getBannerImageLandscapeUri()).zzg("BannerImageLandscapeUrl", player.getBannerImageLandscapeUrl()).zzg("BannerImagePortraitUri", player.getBannerImagePortraitUri()).zzg("BannerImagePortraitUrl", player.getBannerImagePortraitUrl()).zzg("GamerFriendStatus", Integer.valueOf(player.zzata())).zzg("GamerFriendUpdateTimestamp", Long.valueOf(player.zzatb())).zzg("IsMuted", Boolean.valueOf(player.isMuted())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Player freeze() {
        return this;
    }

    public final Uri getBannerImageLandscapeUri() {
        return this.zzhtn;
    }

    public final String getBannerImageLandscapeUrl() {
        return this.zzhto;
    }

    public final Uri getBannerImagePortraitUri() {
        return this.zzhtp;
    }

    public final String getBannerImagePortraitUrl() {
        return this.zzhtq;
    }

    public final String getDisplayName() {
        return this.zzemi;
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzemi, charArrayBuffer);
    }

    public final Uri getHiResImageUri() {
        return this.zzhrb;
    }

    public final String getHiResImageUrl() {
        return this.zzhrm;
    }

    public final Uri getIconImageUri() {
        return this.zzhra;
    }

    public final String getIconImageUrl() {
        return this.zzhrl;
    }

    public final long getLastPlayedWithTimestamp() {
        return this.zzhth;
    }

    public final PlayerLevelInfo getLevelInfo() {
        return this.zzhtj;
    }

    public final String getName() {
        return this.mName;
    }

    public final String getPlayerId() {
        return this.zzfld;
    }

    public final long getRetrievedTimestamp() {
        return this.zzhtf;
    }

    public final String getTitle() {
        return this.zzesj;
    }

    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzesj, charArrayBuffer);
    }

    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isMuted() {
        return this.zzhtt;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getPlayerId(), false);
        zzbgo.zza(parcel, 2, getDisplayName(), false);
        zzbgo.zza(parcel, 3, (Parcelable) getIconImageUri(), i, false);
        zzbgo.zza(parcel, 4, (Parcelable) getHiResImageUri(), i, false);
        zzbgo.zza(parcel, 5, getRetrievedTimestamp());
        zzbgo.zzc(parcel, 6, this.zzhtg);
        zzbgo.zza(parcel, 7, getLastPlayedWithTimestamp());
        zzbgo.zza(parcel, 8, getIconImageUrl(), false);
        zzbgo.zza(parcel, 9, getHiResImageUrl(), false);
        zzbgo.zza(parcel, 14, getTitle(), false);
        zzbgo.zza(parcel, 15, (Parcelable) this.zzhti, i, false);
        zzbgo.zza(parcel, 16, (Parcelable) getLevelInfo(), i, false);
        zzbgo.zza(parcel, 18, this.zzhtk);
        zzbgo.zza(parcel, 19, this.zzhtl);
        zzbgo.zza(parcel, 20, this.zzhtm, false);
        zzbgo.zza(parcel, 21, this.mName, false);
        zzbgo.zza(parcel, 22, (Parcelable) getBannerImageLandscapeUri(), i, false);
        zzbgo.zza(parcel, 23, getBannerImageLandscapeUrl(), false);
        zzbgo.zza(parcel, 24, (Parcelable) getBannerImagePortraitUri(), i, false);
        zzbgo.zza(parcel, 25, getBannerImagePortraitUrl(), false);
        zzbgo.zzc(parcel, 26, this.zzhtr);
        zzbgo.zza(parcel, 27, this.zzhts);
        zzbgo.zza(parcel, 28, this.zzhtt);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzasv() {
        return this.zzhtm;
    }

    public final boolean zzasw() {
        return this.zzhtl;
    }

    public final int zzasx() {
        return this.zzhtg;
    }

    public final boolean zzasy() {
        return this.zzhtk;
    }

    public final com.google.android.gms.games.internal.player.zza zzasz() {
        return this.zzhti;
    }

    public final int zzata() {
        return this.zzhtr;
    }

    public final long zzatb() {
        return this.zzhts;
    }
}
