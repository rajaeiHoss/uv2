package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzc;

public final class zzbme implements Parcelable.Creator<zzbmd> {
    public final zzbmd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzc zzc = null;
        Boolean bool = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                zzc = (zzc) zzbgm.zza(parcel, readInt, zzc.CREATOR);
            } else if (i2 == 3) {
                bool = zzbgm.zzd(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbmd(zzc, bool, i);
    }

    public final zzbmd[] newArray(int i) {
        return new zzbmd[i];
    }
}
