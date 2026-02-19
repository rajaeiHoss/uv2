package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbkk implements Parcelable.Creator<zzbkj> {
    public final zzbkj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<zzbjp> arrayList = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i3 == 4) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 5) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzbjp.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbkj(i, j, str, i2, arrayList);
    }

    public final zzbkj[] newArray(int i) {
        return new zzbkj[i];
    }
}
