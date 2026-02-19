package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctw implements Parcelable.Creator<zzctv> {
    public final zzctv createFromParcel(Parcel parcel) {
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
        return new zzctv(i);
    }

    public final zzctv[] newArray(int i) {
        return new zzctv[i];
    }
}
