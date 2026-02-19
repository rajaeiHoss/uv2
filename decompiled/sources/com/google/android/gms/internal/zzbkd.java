package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbkd implements Parcelable.Creator<zzbkc> {
    public final zzbkc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        long j = 0;
        zzbke zzbke = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                zzbke = (zzbke) zzbgm.zza(parcel, readInt, zzbke.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbkc(str, zzbke, j);
    }

    public final zzbkc[] newArray(int i) {
        return new zzbkc[i];
    }
}
