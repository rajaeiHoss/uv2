package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzawq implements Parcelable.Creator<zzawp> {
    public final zzawp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzawp(i, str, i2);
    }

    public final zzawp[] newArray(int i) {
        return new zzawp[i];
    }
}
