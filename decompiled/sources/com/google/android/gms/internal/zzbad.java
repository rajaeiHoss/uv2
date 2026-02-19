package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbad implements Parcelable.Creator<zzbac> {
    public final zzbac createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int[] iArr = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                f = zzbgm.zzl(parcel, readInt);
            } else if (i2 == 3) {
                f2 = zzbgm.zzl(parcel, readInt);
            } else if (i2 == 4) {
                f3 = zzbgm.zzl(parcel, readInt);
            } else if (i2 == 5) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iArr = zzbgm.zzw(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbac(f, f2, f3, i, iArr);
    }

    public final zzbac[] newArray(int i) {
        return new zzbac[i];
    }
}
