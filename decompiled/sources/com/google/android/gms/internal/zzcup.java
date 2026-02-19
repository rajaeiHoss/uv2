package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcup implements Parcelable.Creator<zzcuo> {
    public final zzcuo createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcuo();
    }

    public final zzcuo[] newArray(int i) {
        return new zzcuo[i];
    }
}
