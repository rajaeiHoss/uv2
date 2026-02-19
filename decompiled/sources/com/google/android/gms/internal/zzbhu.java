package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbhu implements Parcelable.Creator<zzbhx> {
    public final zzbhx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        zzbhq zzbhq = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbhq = (zzbhq) zzbgm.zza(parcel, readInt, zzbhq.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbhx(i, str, zzbhq);
    }

    public final zzbhx[] newArray(int i) {
        return new zzbhx[i];
    }
}
