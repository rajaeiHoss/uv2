package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzaub implements Parcelable.Creator<zzaua> {
    public final zzaua createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str3 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaua(str, str2, str3);
    }

    public final zzaua[] newArray(int i) {
        return new zzaua[i];
    }
}
