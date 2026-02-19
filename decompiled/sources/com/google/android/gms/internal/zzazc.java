package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzazc implements Parcelable.Creator<zzazb> {
    public final zzazb createFromParcel(Parcel parcel) {
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
        return new zzazb(i);
    }

    public final zzazb[] newArray(int i) {
        return new zzazb[i];
    }
}
