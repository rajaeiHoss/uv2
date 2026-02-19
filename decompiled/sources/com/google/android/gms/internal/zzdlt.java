package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlt implements Parcelable.Creator<zzdls> {
    public final zzdls createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdls();
    }

    public final zzdls[] newArray(int i) {
        return new zzdls[i];
    }
}
