package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlg implements Parcelable.Creator<zzdlf> {
    public final zzdlf createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i5 = 65535 & readInt;
            if (i5 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i5 == 3) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i5 == 4) {
                i3 = zzbgm.zzg(parcel, readInt);
            } else if (i5 == 5) {
                i4 = zzbgm.zzg(parcel, readInt);
            } else if (i5 != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdlf(i, i2, i3, i4, f);
    }

    public final zzdlf[] newArray(int i) {
        return new zzdlf[i];
    }
}
