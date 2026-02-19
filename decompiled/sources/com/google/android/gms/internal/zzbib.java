package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbib implements Parcelable.Creator<zzbia> {
    public final zzbia createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Parcel parcel2 = null;
        zzbhv zzbhv = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                parcel2 = zzbgm.zzad(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbhv = (zzbhv) zzbgm.zza(parcel, readInt, zzbhv.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbia(i, parcel2, zzbhv);
    }

    public final zzbia[] newArray(int i) {
        return new zzbia[i];
    }
}
