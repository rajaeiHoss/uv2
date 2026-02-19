package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzafj implements Parcelable.Creator<zzafi> {
    public final zzafi createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzkk zzkk = null;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                zzkk = (zzkk) zzbgm.zza(parcel, readInt, zzkk.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzafi(zzkk, str);
    }

    public final zzafi[] newArray(int i) {
        return new zzafi[i];
    }
}
