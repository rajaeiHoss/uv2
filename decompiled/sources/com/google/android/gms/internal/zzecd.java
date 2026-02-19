package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecd implements Parcelable.Creator<zzecc> {
    public final zzecc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str3 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzecc(str, str2, str3);
    }

    public final zzecc[] newArray(int i) {
        return new zzecc[i];
    }
}
