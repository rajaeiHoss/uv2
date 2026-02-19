package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzmt implements Parcelable.Creator<zzms> {
    public final zzms createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzms(i);
    }

    public final zzms[] newArray(int i) {
        return new zzms[i];
    }
}
