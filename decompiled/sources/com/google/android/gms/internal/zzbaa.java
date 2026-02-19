package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbaa implements Parcelable.Creator<zzazz> {
    public final zzazz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int[] iArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iArr = zzbgm.zzw(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzazz(iArr);
    }

    public final zzazz[] newArray(int i) {
        return new zzazz[i];
    }
}
