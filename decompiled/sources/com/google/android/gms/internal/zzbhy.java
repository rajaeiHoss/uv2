package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbhy implements Parcelable.Creator<zzbhv> {
    public final zzbhv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzbhw> arrayList = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, zzbhw.CREATOR);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbhv(i, arrayList, str);
    }

    public final zzbhv[] newArray(int i) {
        return new zzbhv[i];
    }
}
