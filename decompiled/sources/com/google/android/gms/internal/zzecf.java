package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecf implements Parcelable.Creator<zzece> {
    public final zzece createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        long j = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzece(str, j, z, str2);
    }

    public final zzece[] newArray(int i) {
        return new zzece[i];
    }
}
