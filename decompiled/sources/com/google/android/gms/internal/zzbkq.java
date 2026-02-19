package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbkq implements Parcelable.Creator<zzbkp> {
    public final zzbkp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzbkz> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzbkz.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbkp(arrayList);
    }

    public final zzbkp[] newArray(int i) {
        return new zzbkp[i];
    }
}
