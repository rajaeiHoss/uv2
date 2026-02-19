package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbhz implements Parcelable.Creator<zzbhw> {
    public final zzbhw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<zzbhx> arrayList = null;
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
                arrayList = zzbgm.zzc(parcel, readInt, zzbhx.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbhw(i, str, arrayList);
    }

    public final zzbhw[] newArray(int i) {
        return new zzbhw[i];
    }
}
