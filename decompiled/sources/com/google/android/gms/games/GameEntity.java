package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Parcelable.Creator<GameEntity> CREATOR = new zza();
    private final boolean zzdoi;
    private final String zzdxh;
    private final String zzemi;
    private final String zzetb;
    private final String zzhqx;
    private final String zzhqy;
    private final String zzhqz;
    private final Uri zzhra;
    private final Uri zzhrb;
    private final Uri zzhrc;
    private final boolean zzhrd;
    private final boolean zzhre;
    private final String zzhrf;
    private final int zzhrg;
    private final int zzhrh;
    private final int zzhri;
    private final boolean zzhrj;
    private final boolean zzhrk;
    private final String zzhrl;
    private final String zzhrm;
    private final String zzhrn;
    private final boolean zzhro;
    private final boolean zzhrp;
    private final String zzhrq;
    private final boolean zzhrr;

    static final class zza extends com.google.android.gms.games.zzh {
        zza() {
        }

        public final GameEntity createFromParcel(Parcel parcel) {
            return zzo(parcel);
        }

        public final GameEntity zzo(Parcel parcel) {
            if (GameEntity.zze(GameEntity.zzamq()) || GameEntity.zzgq(GameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            Uri parse = readString7 == null ? null : Uri.parse(readString7);
            String readString8 = parcel.readString();
            Uri parse2 = readString8 == null ? null : Uri.parse(readString8);
            String readString9 = parcel.readString();
            return new GameEntity(readString, readString2, readString3, readString4, readString5, readString6, parse, parse2, readString9 == null ? null : Uri.parse(readString9), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, (String) null, (String) null, (String) null, false, false, false, (String) null, false);
        }
    }

    public GameEntity(Game game) {
        this.zzetb = game.getApplicationId();
        this.zzhqx = game.getPrimaryCategory();
        this.zzhqy = game.getSecondaryCategory();
        this.zzdxh = game.getDescription();
        this.zzhqz = game.getDeveloperName();
        this.zzemi = game.getDisplayName();
        this.zzhra = game.getIconImageUri();
        this.zzhrl = game.getIconImageUrl();
        this.zzhrb = game.getHiResImageUri();
        this.zzhrm = game.getHiResImageUrl();
        this.zzhrc = game.getFeaturedImageUri();
        this.zzhrn = game.getFeaturedImageUrl();
        this.zzhrd = game.zzasp();
        this.zzhre = game.zzasr();
        this.zzhrf = game.zzass();
        this.zzhrg = 1;
        this.zzhrh = game.getAchievementTotalCount();
        this.zzhri = game.getLeaderboardCount();
        this.zzhrj = game.isRealTimeMultiplayerEnabled();
        this.zzhrk = game.isTurnBasedMultiplayerEnabled();
        this.zzdoi = game.isMuted();
        this.zzhro = game.zzasq();
        this.zzhrp = game.areSnapshotsEnabled();
        this.zzhrq = game.getThemeColor();
        this.zzhrr = game.hasGamepadSupport();
    }

    GameEntity(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Uri uri2, Uri uri3, boolean z, boolean z2, String str7, int i, int i2, int i3, boolean z3, boolean z4, String str8, String str9, String str10, boolean z5, boolean z6, boolean z7, String str11, boolean z8) {
        this.zzetb = str;
        this.zzemi = str2;
        this.zzhqx = str3;
        this.zzhqy = str4;
        this.zzdxh = str5;
        this.zzhqz = str6;
        this.zzhra = uri;
        this.zzhrl = str8;
        this.zzhrb = uri2;
        this.zzhrm = str9;
        this.zzhrc = uri3;
        this.zzhrn = str10;
        this.zzhrd = z;
        this.zzhre = z2;
        this.zzhrf = str7;
        this.zzhrg = i;
        this.zzhrh = i2;
        this.zzhri = i3;
        this.zzhrj = z3;
        this.zzhrk = z4;
        this.zzdoi = z5;
        this.zzhro = z6;
        this.zzhrp = z7;
        this.zzhrq = str11;
        this.zzhrr = z8;
    }

    static int zza(Game game) {
        return Arrays.hashCode(new Object[]{game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.zzasp()), Boolean.valueOf(game.zzasr()), game.zzass(), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isMuted()), Boolean.valueOf(game.zzasq()), Boolean.valueOf(game.areSnapshotsEnabled()), game.getThemeColor(), Boolean.valueOf(game.hasGamepadSupport())});
    }

    static boolean zza(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        if (zzbg.equal(game2.getApplicationId(), game.getApplicationId()) && zzbg.equal(game2.getDisplayName(), game.getDisplayName()) && zzbg.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && zzbg.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && zzbg.equal(game2.getDescription(), game.getDescription()) && zzbg.equal(game2.getDeveloperName(), game.getDeveloperName()) && zzbg.equal(game2.getIconImageUri(), game.getIconImageUri()) && zzbg.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && zzbg.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && zzbg.equal(Boolean.valueOf(game2.zzasp()), Boolean.valueOf(game.zzasp())) && zzbg.equal(Boolean.valueOf(game2.zzasr()), Boolean.valueOf(game.zzasr())) && zzbg.equal(game2.zzass(), game.zzass()) && zzbg.equal(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && zzbg.equal(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) && zzbg.equal(Boolean.valueOf(game2.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()))) {
            return zzbg.equal(Boolean.valueOf(game2.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled() && zzbg.equal(Boolean.valueOf(game2.isMuted()), Boolean.valueOf(game.isMuted())) && zzbg.equal(Boolean.valueOf(game2.zzasq()), Boolean.valueOf(game.zzasq())))) && zzbg.equal(Boolean.valueOf(game2.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled())) && zzbg.equal(game2.getThemeColor(), game.getThemeColor()) && zzbg.equal(Boolean.valueOf(game2.hasGamepadSupport()), Boolean.valueOf(game.hasGamepadSupport()));
        }
        return false;
    }

    static String zzb(Game game) {
        return zzbg.zzx(game).zzg("ApplicationId", game.getApplicationId()).zzg("DisplayName", game.getDisplayName()).zzg("PrimaryCategory", game.getPrimaryCategory()).zzg("SecondaryCategory", game.getSecondaryCategory()).zzg("Description", game.getDescription()).zzg("DeveloperName", game.getDeveloperName()).zzg("IconImageUri", game.getIconImageUri()).zzg("IconImageUrl", game.getIconImageUrl()).zzg("HiResImageUri", game.getHiResImageUri()).zzg("HiResImageUrl", game.getHiResImageUrl()).zzg("FeaturedImageUri", game.getFeaturedImageUri()).zzg("FeaturedImageUrl", game.getFeaturedImageUrl()).zzg("PlayEnabledGame", Boolean.valueOf(game.zzasp())).zzg("InstanceInstalled", Boolean.valueOf(game.zzasr())).zzg("InstancePackageName", game.zzass()).zzg("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).zzg("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).zzg("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).zzg("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).zzg("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).zzg("ThemeColor", game.getThemeColor()).zzg("HasGamepadSupport", Boolean.valueOf(game.hasGamepadSupport())).toString();
    }

    public final boolean areSnapshotsEnabled() {
        return this.zzhrp;
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Game freeze() {
        return this;
    }

    public final int getAchievementTotalCount() {
        return this.zzhrh;
    }

    public final String getApplicationId() {
        return this.zzetb;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final String getDeveloperName() {
        return this.zzhqz;
    }

    public final void getDeveloperName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzhqz, charArrayBuffer);
    }

    public final String getDisplayName() {
        return this.zzemi;
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzemi, charArrayBuffer);
    }

    public final Uri getFeaturedImageUri() {
        return this.zzhrc;
    }

    public final String getFeaturedImageUrl() {
        return this.zzhrn;
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

    public final int getLeaderboardCount() {
        return this.zzhri;
    }

    public final String getPrimaryCategory() {
        return this.zzhqx;
    }

    public final String getSecondaryCategory() {
        return this.zzhqy;
    }

    public final String getThemeColor() {
        return this.zzhrq;
    }

    public final boolean hasGamepadSupport() {
        return this.zzhrr;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isMuted() {
        return this.zzdoi;
    }

    public final boolean isRealTimeMultiplayerEnabled() {
        return this.zzhrj;
    }

    public final boolean isTurnBasedMultiplayerEnabled() {
        return this.zzhrk;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getApplicationId(), false);
        zzbgo.zza(parcel, 2, getDisplayName(), false);
        zzbgo.zza(parcel, 3, getPrimaryCategory(), false);
        zzbgo.zza(parcel, 4, getSecondaryCategory(), false);
        zzbgo.zza(parcel, 5, getDescription(), false);
        zzbgo.zza(parcel, 6, getDeveloperName(), false);
        zzbgo.zza(parcel, 7, (Parcelable) getIconImageUri(), i, false);
        zzbgo.zza(parcel, 8, (Parcelable) getHiResImageUri(), i, false);
        zzbgo.zza(parcel, 9, (Parcelable) getFeaturedImageUri(), i, false);
        zzbgo.zza(parcel, 10, this.zzhrd);
        zzbgo.zza(parcel, 11, this.zzhre);
        zzbgo.zza(parcel, 12, this.zzhrf, false);
        zzbgo.zzc(parcel, 13, this.zzhrg);
        zzbgo.zzc(parcel, 14, getAchievementTotalCount());
        zzbgo.zzc(parcel, 15, getLeaderboardCount());
        zzbgo.zza(parcel, 16, isRealTimeMultiplayerEnabled());
        zzbgo.zza(parcel, 17, isTurnBasedMultiplayerEnabled());
        zzbgo.zza(parcel, 18, getIconImageUrl(), false);
        zzbgo.zza(parcel, 19, getHiResImageUrl(), false);
        zzbgo.zza(parcel, 20, getFeaturedImageUrl(), false);
        zzbgo.zza(parcel, 21, this.zzdoi);
        zzbgo.zza(parcel, 22, this.zzhro);
        zzbgo.zza(parcel, 23, areSnapshotsEnabled());
        zzbgo.zza(parcel, 24, getThemeColor(), false);
        zzbgo.zza(parcel, 25, hasGamepadSupport());
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zzasp() {
        return this.zzhrd;
    }

    public final boolean zzasq() {
        return this.zzhro;
    }

    public final boolean zzasr() {
        return this.zzhre;
    }

    public final String zzass() {
        return this.zzhrf;
    }
}
