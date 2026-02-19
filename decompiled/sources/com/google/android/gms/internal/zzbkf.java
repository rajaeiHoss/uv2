package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbkf implements Parcelable.Creator<zzbke> {
    public final zzbke createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbke(bArr);
    }

    public final zzbke[] newArray(int i) {
        return new zzbke[i];
    }
}
