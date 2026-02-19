package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public final class zzcbz implements Parcelable.Creator<zzcby> {
    public final zzcby createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<DataType> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, DataType.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcby(arrayList);
    }

    public final zzcby[] newArray(int i) {
        return new zzcby[i];
    }
}
