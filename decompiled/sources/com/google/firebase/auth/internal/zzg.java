package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<zzf> {
    public final zzf createFromParcel(Parcel parcel) {
        int end = zzbgm.zzd(parcel);
        zzk zzk = null;
        zzd zzd2 = null;
        com.google.firebase.auth.zzd zzd3 = null;
        while (parcel.dataPosition() < end) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                zzk = (zzk) zzbgm.zza(parcel, readInt, zzk.CREATOR);
            } else if (i == 2) {
                zzd2 = (zzd) zzbgm.zza(parcel, readInt, zzd.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzd3 = (com.google.firebase.auth.zzd) zzbgm.zza(parcel, readInt, com.google.firebase.auth.zzd.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, end);
        return new zzf(zzk, zzd2, zzd3);
    }

    public final zzf[] newArray(int i) {
        return new zzf[i];
    }
}
