package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgm;

public final class zzp implements Parcelable.Creator<Goal.DurationObjective> {
    public final /* synthetic */ Goal.DurationObjective createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Goal.DurationObjective(j);
    }

    public final /* synthetic */ Goal.DurationObjective[] newArray(int i) {
        return new Goal.DurationObjective[i];
    }
}
