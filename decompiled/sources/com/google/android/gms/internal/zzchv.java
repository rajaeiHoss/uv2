package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzchv implements Parcelable.Creator<zzchu> {
    public final zzchu createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzchu(str);
    }

    public final zzchu[] newArray(int i) {
        return new zzchu[i];
    }
}
