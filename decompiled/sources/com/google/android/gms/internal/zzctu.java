package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctu implements Parcelable.Creator<zzctt> {
    public final zzctt createFromParcel(Parcel parcel) {
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
        return new zzctt(i, str);
    }

    public final zzctt[] newArray(int i) {
        return new zzctt[i];
    }
}
