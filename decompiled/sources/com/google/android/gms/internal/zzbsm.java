package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;

public final class zzbsm implements Parcelable.Creator<zzbsl> {
    public final zzbsl createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataHolder = (DataHolder) zzbgm.zza(parcel, readInt, DataHolder.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsl(dataHolder);
    }

    public final zzbsl[] newArray(int i) {
        return new zzbsl[i];
    }
}
