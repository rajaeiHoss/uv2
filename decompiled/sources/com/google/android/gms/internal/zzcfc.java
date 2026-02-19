package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcfc implements Parcelable.Creator<zzcfb> {
    public final zzcfb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String[] strArr4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                strArr = zzbgm.zzaa(parcel, readInt);
            } else if (i == 2) {
                strArr2 = zzbgm.zzaa(parcel, readInt);
            } else if (i == 3) {
                strArr4 = zzbgm.zzaa(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                strArr3 = zzbgm.zzaa(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcfb(strArr, strArr2, strArr3, strArr4);
    }

    public final zzcfb[] newArray(int i) {
        return new zzcfb[i];
    }
}
