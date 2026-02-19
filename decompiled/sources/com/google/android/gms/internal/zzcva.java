package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcva implements Parcelable.Creator<zzcuz> {
    public final zzcuz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        byte[] bArr = null;
        int i2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i3 == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i3 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcuz(i, i2, bArr, z);
    }

    public final zzcuz[] newArray(int i) {
        return new zzcuz[i];
    }
}
