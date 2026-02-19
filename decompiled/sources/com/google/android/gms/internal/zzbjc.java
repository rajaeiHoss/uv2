package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbjc implements Parcelable.Creator<zzbjb> {
    public final zzbjb createFromParcel(Parcel parcel) {
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
        return new zzbjb(bArr);
    }

    public final zzbjb[] newArray(int i) {
        return new zzbjb[i];
    }
}
