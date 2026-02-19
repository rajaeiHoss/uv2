package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbmt implements Parcelable.Creator<zzbms> {
    public final zzbms createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbms();
    }

    public final zzbms[] newArray(int i) {
        return new zzbms[i];
    }
}
