package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlq implements Parcelable.Creator<zzdlp> {
    public final zzdlp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdlp();
    }

    public final zzdlp[] newArray(int i) {
        return new zzdlp[i];
    }
}
