package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public final class zzb extends zzc implements Milestone {
    zzb(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return MilestoneEntity.zza(this, obj);
    }

    public final Milestone freeze() {
        return new MilestoneEntity(this);
    }

    public final byte[] getCompletionRewardData() {
        return getByteArray("completion_reward_data");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long getCurrentProgress() {
        /*
            r9 = this;
            int r0 = r9.getState()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0032
            r1 = 2
            if (r0 == r1) goto L_0x0018
            r1 = 3
            if (r0 == r1) goto L_0x0013
            r1 = 4
            if (r0 == r1) goto L_0x0013
            goto L_0x0032
        L_0x0013:
            long r0 = r9.getTargetProgress()
            goto L_0x0033
        L_0x0018:
            java.lang.String r0 = "current_value"
            long r0 = r9.getLong(r0)
            java.lang.String r4 = "quest_state"
            long r4 = r9.getLong(r4)
            r6 = 6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0033
            java.lang.String r4 = "initial_value"
            long r4 = r9.getLong(r4)
            long r0 = r0 - r4
            goto L_0x0033
        L_0x0032:
            r0 = r2
        L_0x0033:
            java.lang.String r4 = "MilestoneRef"
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x003f
            java.lang.String r0 = "Current progress should never be negative"
            com.google.android.gms.games.internal.zzf.zzw(r4, r0)
            goto L_0x0040
        L_0x003f:
            r2 = r0
        L_0x0040:
            long r0 = r9.getTargetProgress()
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 <= 0) goto L_0x0051
            java.lang.String r0 = "Current progress should never exceed target progress"
            com.google.android.gms.games.internal.zzf.zzw(r4, r0)
            long r2 = r9.getTargetProgress()
        L_0x0051:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.quest.zzb.getCurrentProgress():long");
    }

    public final String getEventId() {
        return getString("external_event_id");
    }

    public final String getMilestoneId() {
        return getString("external_milestone_id");
    }

    public final int getState() {
        return getInteger("milestone_state");
    }

    public final long getTargetProgress() {
        return getLong("target_value");
    }

    public final int hashCode() {
        return MilestoneEntity.zza(this);
    }

    public final String toString() {
        return MilestoneEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((MilestoneEntity) ((Milestone) freeze())).writeToParcel(parcel, i);
    }
}
