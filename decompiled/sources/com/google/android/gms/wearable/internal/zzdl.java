package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzdl implements Parcelable.Creator<zzdk> {
    public final zzdk createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzah zzah = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzah = (zzah) zzbgm.zza(parcel, readInt, zzah.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdk(i, zzah);
    }

    public final zzdk[] newArray(int i) {
        return new zzdk[i];
    }
}
