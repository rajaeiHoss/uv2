package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

@Deprecated
public final class MilestoneEntity extends zzc implements Milestone {
    public static final Parcelable.Creator<MilestoneEntity> CREATOR = new zza();
    private final int mState;
    private final String zzhwp;
    private final String zzhyo;
    private final long zzigs;
    private final long zzigt;
    private final byte[] zzigu;

    public MilestoneEntity(Milestone milestone) {
        this.zzhyo = milestone.getMilestoneId();
        this.zzigs = milestone.getCurrentProgress();
        this.zzigt = milestone.getTargetProgress();
        this.mState = milestone.getState();
        this.zzhwp = milestone.getEventId();
        byte[] completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.zzigu = null;
            return;
        }
        byte[] bArr = new byte[completionRewardData.length];
        this.zzigu = bArr;
        System.arraycopy(completionRewardData, 0, bArr, 0, completionRewardData.length);
    }

    MilestoneEntity(String str, long j, long j2, byte[] bArr, int i, String str2) {
        this.zzhyo = str;
        this.zzigs = j;
        this.zzigt = j2;
        this.zzigu = bArr;
        this.mState = i;
        this.zzhwp = str2;
    }

    static int zza(Milestone milestone) {
        return Arrays.hashCode(new Object[]{milestone.getMilestoneId(), Long.valueOf(milestone.getCurrentProgress()), Long.valueOf(milestone.getTargetProgress()), Integer.valueOf(milestone.getState()), milestone.getEventId()});
    }

    static boolean zza(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return zzbg.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && zzbg.equal(Long.valueOf(milestone2.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && zzbg.equal(Long.valueOf(milestone2.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && zzbg.equal(Integer.valueOf(milestone2.getState()), Integer.valueOf(milestone.getState())) && zzbg.equal(milestone2.getEventId(), milestone.getEventId());
    }

    static String zzb(Milestone milestone) {
        return zzbg.zzx(milestone).zzg("MilestoneId", milestone.getMilestoneId()).zzg("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).zzg("TargetProgress", Long.valueOf(milestone.getTargetProgress())).zzg("State", Integer.valueOf(milestone.getState())).zzg("CompletionRewardData", milestone.getCompletionRewardData()).zzg("EventId", milestone.getEventId()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Milestone freeze() {
        return this;
    }

    public final byte[] getCompletionRewardData() {
        return this.zzigu;
    }

    public final long getCurrentProgress() {
        return this.zzigs;
    }

    public final String getEventId() {
        return this.zzhwp;
    }

    public final String getMilestoneId() {
        return this.zzhyo;
    }

    public final int getState() {
        return this.mState;
    }

    public final long getTargetProgress() {
        return this.zzigt;
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
        zzbgo.zza(parcel, 1, getMilestoneId(), false);
        zzbgo.zza(parcel, 2, getCurrentProgress());
        zzbgo.zza(parcel, 3, getTargetProgress());
        zzbgo.zza(parcel, 4, getCompletionRewardData(), false);
        zzbgo.zzc(parcel, 5, getState());
        zzbgo.zza(parcel, 6, getEventId(), false);
        zzbgo.zzai(parcel, zze);
    }
}
