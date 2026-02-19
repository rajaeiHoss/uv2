package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcou implements Parcelable.Creator<zzcot> {
    public final zzcot createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcot();
    }

    public final zzcot[] newArray(int i) {
        return new zzcot[i];
    }
}
