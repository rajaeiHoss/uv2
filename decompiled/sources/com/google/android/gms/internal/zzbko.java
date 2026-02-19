package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbko implements Parcelable.Creator<zzbkn> {
    public final zzbkn createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbkn(z, str);
    }

    public final zzbkn[] newArray(int i) {
        return new zzbkn[i];
    }
}
