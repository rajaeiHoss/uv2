package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;

public final class zzbsk implements Parcelable.Creator<zzbsj> {
    public final zzbsj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataHolder dataHolder = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                dataHolder = (DataHolder) zzbgm.zza(parcel, readInt, DataHolder.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsj(dataHolder, z);
    }

    public final zzbsj[] newArray(int i) {
        return new zzbsj[i];
    }
}
