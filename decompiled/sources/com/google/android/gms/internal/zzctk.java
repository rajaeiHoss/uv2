package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctk implements Parcelable.Creator<zzctj> {
    public final zzctj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctj(str);
    }

    public final zzctj[] newArray(int i) {
        return new zzctj[i];
    }
}
