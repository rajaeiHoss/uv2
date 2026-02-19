package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcut implements Parcelable.Creator<zzcus> {
    public final zzcus createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcus();
    }

    public final zzcus[] newArray(int i) {
        return new zzcus[i];
    }
}
