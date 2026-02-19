package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzfv implements Parcelable.Creator<zzfu> {
    public final zzfu createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzdd zzdd = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzdd = (zzdd) zzbgm.zza(parcel, readInt, zzdd.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzfu(i, zzdd);
    }

    public final zzfu[] newArray(int i) {
        return new zzfu[i];
    }
}
