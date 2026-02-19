package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbde implements Parcelable.Creator<zzbdd> {
    public final zzbdd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbdd(str);
    }

    public final zzbdd[] newArray(int i) {
        return new zzbdd[i];
    }
}
