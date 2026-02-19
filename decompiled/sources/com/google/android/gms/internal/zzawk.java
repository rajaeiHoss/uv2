package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzawk implements Parcelable.Creator<zzawj> {
    public final zzawj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzawj(i, str);
    }

    public final zzawj[] newArray(int i) {
        return new zzawj[i];
    }
}
