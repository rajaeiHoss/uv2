package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzchy implements Parcelable.Creator<zzchw> {
    public final zzchw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        ArrayList<zzchu> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzchu.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzchw(str, str2, arrayList);
    }

    public final zzchw[] newArray(int i) {
        return new zzchw[i];
    }
}
