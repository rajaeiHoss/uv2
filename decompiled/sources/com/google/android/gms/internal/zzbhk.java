package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbhk implements Parcelable.Creator<zzbhj> {
    public final zzbhj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzbhl zzbhl = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbhl = (zzbhl) zzbgm.zza(parcel, readInt, zzbhl.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbhj(i, zzbhl);
    }

    public final zzbhj[] newArray(int i) {
        return new zzbhj[i];
    }
}
