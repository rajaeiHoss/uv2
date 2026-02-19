package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbr;

public final class zzcyv implements Parcelable.Creator<zzcyu> {
    public final zzcyu createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzbr zzbr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbr = (zzbr) zzbgm.zza(parcel, readInt, zzbr.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcyu(i, zzbr);
    }

    public final zzcyu[] newArray(int i) {
        return new zzcyu[i];
    }
}
