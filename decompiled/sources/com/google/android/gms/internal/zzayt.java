package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzayt implements Parcelable.Creator<zzays> {
    public final zzays createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzays(i);
    }

    public final zzays[] newArray(int i) {
        return new zzays[i];
    }
}
