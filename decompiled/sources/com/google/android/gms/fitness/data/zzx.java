package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgm;

public final class zzx implements Parcelable.Creator<Goal.MetricObjective> {
    public final /* synthetic */ Goal.MetricObjective createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        double d = 0.0d;
        double d2 = 0.0d;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                d = zzbgm.zzn(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                d2 = zzbgm.zzn(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Goal.MetricObjective(str, d, d2);
    }

    public final /* synthetic */ Goal.MetricObjective[] newArray(int i) {
        return new Goal.MetricObjective[i];
    }
}
