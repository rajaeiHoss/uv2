package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcft implements Parcelable.Creator<zzcfs> {
    public final zzcfs createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcfs(i, str);
    }

    public final zzcfs[] newArray(int i) {
        return new zzcfs[i];
    }
}
