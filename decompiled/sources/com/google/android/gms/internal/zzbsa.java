package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsa implements Parcelable.Creator<zzbrz> {
    public final zzbrz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzbre zzbre = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbre = (zzbre) zzbgm.zza(parcel, readInt, zzbre.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbrz(zzbre);
    }

    public final zzbrz[] newArray(int i) {
        return new zzbrz[i];
    }
}
