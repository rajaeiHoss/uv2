package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbjr implements Parcelable.Creator<zzbjp> {
    public final zzbjp createFromParcel(Parcel parcel) {
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
        return new zzbjp(bArr);
    }

    public final zzbjp[] newArray(int i) {
        return new zzbjp[i];
    }
}
