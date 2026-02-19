package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzayv implements Parcelable.Creator<zzayu> {
    public final zzayu createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzayu(i, i2);
    }

    public final zzayu[] newArray(int i) {
        return new zzayu[i];
    }
}
