package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzebx implements Parcelable.Creator<zzebw> {
    public final zzebw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        Long l = null;
        String str3 = null;
        Long l2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                l = zzbgm.zzj(parcel, readInt);
            } else if (i == 5) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                l2 = zzbgm.zzj(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzebw(str, str2, l, str3, l2);
    }

    public final zzebw[] newArray(int i) {
        return new zzebw[i];
    }
}
