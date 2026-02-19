package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzd implements Parcelable.Creator<ActivityTransitionEvent> {
    public final /* synthetic */ ActivityTransitionEvent createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ActivityTransitionEvent(i, i2, j);
    }

    public final /* synthetic */ ActivityTransitionEvent[] newArray(int i) {
        return new ActivityTransitionEvent[i];
    }
}
