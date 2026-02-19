package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbrf implements Parcelable.Creator<zzbre> {
    public final zzbre createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbre(i, i2, z);
    }

    public final zzbre[] newArray(int i) {
        return new zzbre[i];
    }
}
