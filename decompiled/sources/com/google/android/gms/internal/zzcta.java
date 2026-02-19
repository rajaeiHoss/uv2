package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcta implements Parcelable.Creator<zzcsz> {
    public final zzcsz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcsz(str, i);
    }

    public final zzcsz[] newArray(int i) {
        return new zzcsz[i];
    }
}
