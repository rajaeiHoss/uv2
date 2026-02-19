package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class QuestEntity extends zzc implements Quest {
    public static final Parcelable.Creator<QuestEntity> CREATOR = new com.google.android.gms.games.quest.zzc();
    private final String mName;
    private final int mState;
    private final String zzdxh;
    private final int zzenu;
    private final long zzhwn;
    private final GameEntity zzibx;
    private final String zzigv;
    private final long zzigw;
    private final Uri zzigx;
    private final String zzigy;
    private final long zzigz;
    private final Uri zziha;
    private final String zzihb;
    private final long zzihc;
    private final long zzihd;
    private final ArrayList<MilestoneEntity> zzihe;

    QuestEntity(GameEntity gameEntity, String str, long j, Uri uri, String str2, String str3, long j2, long j3, Uri uri2, String str4, String str5, long j4, long j5, int i, int i2, ArrayList<MilestoneEntity> arrayList) {
        this.zzibx = gameEntity;
        this.zzigv = str;
        this.zzigw = j;
        this.zzigx = uri;
        this.zzigy = str2;
        this.zzdxh = str3;
        this.zzigz = j2;
        this.zzhwn = j3;
        this.zziha = uri2;
        this.zzihb = str4;
        this.mName = str5;
        this.zzihc = j4;
        this.zzihd = j5;
        this.mState = i;
        this.zzenu = i2;
        this.zzihe = arrayList;
    }

    public QuestEntity(Quest quest) {
        this.zzibx = new GameEntity(quest.getGame());
        this.zzigv = quest.getQuestId();
        this.zzigw = quest.getAcceptedTimestamp();
        this.zzdxh = quest.getDescription();
        this.zzigx = quest.getBannerImageUri();
        this.zzigy = quest.getBannerImageUrl();
        this.zzigz = quest.getEndTimestamp();
        this.zziha = quest.getIconImageUri();
        this.zzihb = quest.getIconImageUrl();
        this.zzhwn = quest.getLastUpdatedTimestamp();
        this.mName = quest.getName();
        this.zzihc = quest.zzavx();
        this.zzihd = quest.getStartTimestamp();
        this.mState = quest.getState();
        this.zzenu = quest.getType();
        List<Milestone> zzavw = quest.zzavw();
        int size = zzavw.size();
        this.zzihe = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.zzihe.add((MilestoneEntity) zzavw.get(i).freeze());
        }
    }

    static int zza(Quest quest) {
        return Arrays.hashCode(new Object[]{quest.getGame(), quest.getQuestId(), Long.valueOf(quest.getAcceptedTimestamp()), quest.getBannerImageUri(), quest.getDescription(), Long.valueOf(quest.getEndTimestamp()), quest.getIconImageUri(), Long.valueOf(quest.getLastUpdatedTimestamp()), quest.zzavw(), quest.getName(), Long.valueOf(quest.zzavx()), Long.valueOf(quest.getStartTimestamp()), Integer.valueOf(quest.getState())});
    }

    static boolean zza(Quest quest, Object obj) {
        if (!(obj instanceof Quest)) {
            return false;
        }
        if (quest == obj) {
            return true;
        }
        Quest quest2 = (Quest) obj;
        return zzbg.equal(quest2.getGame(), quest.getGame()) && zzbg.equal(quest2.getQuestId(), quest.getQuestId()) && zzbg.equal(Long.valueOf(quest2.getAcceptedTimestamp()), Long.valueOf(quest.getAcceptedTimestamp())) && zzbg.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && zzbg.equal(quest2.getDescription(), quest.getDescription()) && zzbg.equal(Long.valueOf(quest2.getEndTimestamp()), Long.valueOf(quest.getEndTimestamp())) && zzbg.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && zzbg.equal(Long.valueOf(quest2.getLastUpdatedTimestamp()), Long.valueOf(quest.getLastUpdatedTimestamp())) && zzbg.equal(quest2.zzavw(), quest.zzavw()) && zzbg.equal(quest2.getName(), quest.getName()) && zzbg.equal(Long.valueOf(quest2.zzavx()), Long.valueOf(quest.zzavx())) && zzbg.equal(Long.valueOf(quest2.getStartTimestamp()), Long.valueOf(quest.getStartTimestamp())) && zzbg.equal(Integer.valueOf(quest2.getState()), Integer.valueOf(quest.getState()));
    }

    static String zzb(Quest quest) {
        return zzbg.zzx(quest).zzg("Game", quest.getGame()).zzg("QuestId", quest.getQuestId()).zzg("AcceptedTimestamp", Long.valueOf(quest.getAcceptedTimestamp())).zzg("BannerImageUri", quest.getBannerImageUri()).zzg("BannerImageUrl", quest.getBannerImageUrl()).zzg("Description", quest.getDescription()).zzg("EndTimestamp", Long.valueOf(quest.getEndTimestamp())).zzg("IconImageUri", quest.getIconImageUri()).zzg("IconImageUrl", quest.getIconImageUrl()).zzg("LastUpdatedTimestamp", Long.valueOf(quest.getLastUpdatedTimestamp())).zzg("Milestones", quest.zzavw()).zzg("Name", quest.getName()).zzg("NotifyTimestamp", Long.valueOf(quest.zzavx())).zzg("StartTimestamp", Long.valueOf(quest.getStartTimestamp())).zzg("State", Integer.valueOf(quest.getState())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Quest freeze() {
        return this;
    }

    public final long getAcceptedTimestamp() {
        return this.zzigw;
    }

    public final Uri getBannerImageUri() {
        return this.zzigx;
    }

    public final String getBannerImageUrl() {
        return this.zzigy;
    }

    public final Milestone getCurrentMilestone() {
        return zzavw().get(0);
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final long getEndTimestamp() {
        return this.zzigz;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final Uri getIconImageUri() {
        return this.zziha;
    }

    public final String getIconImageUrl() {
        return this.zzihb;
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

    public final String getQuestId() {
        return this.zzigv;
    }

    public final long getStartTimestamp() {
        return this.zzihd;
    }

    public final int getState() {
        return this.mState;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isEndingSoon() {
        return this.zzihc <= System.currentTimeMillis() + 1800000;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getGame(), i, false);
        zzbgo.zza(parcel, 2, getQuestId(), false);
        zzbgo.zza(parcel, 3, getAcceptedTimestamp());
        zzbgo.zza(parcel, 4, (Parcelable) getBannerImageUri(), i, false);
        zzbgo.zza(parcel, 5, getBannerImageUrl(), false);
        zzbgo.zza(parcel, 6, getDescription(), false);
        zzbgo.zza(parcel, 7, getEndTimestamp());
        zzbgo.zza(parcel, 8, getLastUpdatedTimestamp());
        zzbgo.zza(parcel, 9, (Parcelable) getIconImageUri(), i, false);
        zzbgo.zza(parcel, 10, getIconImageUrl(), false);
        zzbgo.zza(parcel, 12, getName(), false);
        zzbgo.zza(parcel, 13, this.zzihc);
        zzbgo.zza(parcel, 14, getStartTimestamp());
        zzbgo.zzc(parcel, 15, getState());
        zzbgo.zzc(parcel, 16, this.zzenu);
        zzbgo.zzc(parcel, 17, zzavw(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<Milestone> zzavw() {
        return new ArrayList(this.zzihe);
    }

    public final long zzavx() {
        return this.zzihc;
    }
}
