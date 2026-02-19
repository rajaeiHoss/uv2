package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbki implements Parcelable.Creator<zzbkg> {
    public final zzbkg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzbkh zzbkh = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbkh = (zzbkh) zzbgm.zza(parcel, readInt, zzbkh.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbkg(zzbkh);
    }

    public final zzbkg[] newArray(int i) {
        return new zzbkg[i];
    }
}
