package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcur implements Parcelable.Creator<zzcuq> {
    public final zzcuq createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcuq();
    }

    public final zzcuq[] newArray(int i) {
        return new zzcuq[i];
    }
}
