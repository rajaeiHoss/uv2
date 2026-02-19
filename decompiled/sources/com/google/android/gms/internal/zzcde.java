package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcde implements Parcelable.Creator<zzcdd> {
    public final zzcdd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcdd(i, str, str2);
    }

    public final zzcdd[] newArray(int i) {
        return new zzcdd[i];
    }
}
