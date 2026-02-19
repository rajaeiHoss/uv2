package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctq implements Parcelable.Creator<zzctp> {
    public final zzctp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        zzcub zzcub = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                zzcub = (zzcub) zzbgm.zza(parcel, readInt, zzcub.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctp(str, zzcub, z);
    }

    public final zzctp[] newArray(int i) {
        return new zzctp[i];
    }
}
