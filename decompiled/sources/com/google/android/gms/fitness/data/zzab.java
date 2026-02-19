package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.internal.zzbgm;

public final class zzab implements Parcelable.Creator<Goal.Recurrence> {
    public final /* synthetic */ Goal.Recurrence createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Goal.Recurrence(i, i2);
    }

    public final /* synthetic */ Goal.Recurrence[] newArray(int i) {
        return new Goal.Recurrence[i];
    }
}
