package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgm;

public final class zzr implements Parcelable.Creator<Goal.FrequencyObjective> {
    public final /* synthetic */ Goal.FrequencyObjective createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Goal.FrequencyObjective(i);
    }

    public final /* synthetic */ Goal.FrequencyObjective[] newArray(int i) {
        return new Goal.FrequencyObjective[i];
    }
}
