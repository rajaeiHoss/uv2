package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsr implements Parcelable.Creator<zzbsq> {
    public final zzbsq createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzbte zzbte = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbte = (zzbte) zzbgm.zza(parcel, readInt, zzbte.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsq(zzbte);
    }

    public final zzbsq[] newArray(int i) {
        return new zzbsq[i];
    }
}
