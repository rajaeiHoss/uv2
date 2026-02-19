package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzage implements Parcelable.Creator<zzagd> {
    public final zzagd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzagd(str, i);
    }

    public final zzagd[] newArray(int i) {
        return new zzagd[i];
    }
}
