package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zznp implements Parcelable.Creator<zzno> {
    public final zzno createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 15) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzno(str);
    }

    public final zzno[] newArray(int i) {
        return new zzno[i];
    }
}
