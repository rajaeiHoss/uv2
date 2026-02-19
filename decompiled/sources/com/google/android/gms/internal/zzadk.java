package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzadk implements Parcelable.Creator<zzadj> {
    public final zzadj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzadj(str);
    }

    public final zzadj[] newArray(int i) {
        return new zzadj[i];
    }
}
