package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzw implements Parcelable.Creator<MapValue> {
    public final /* synthetic */ MapValue createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new MapValue(i, f);
    }

    public final /* synthetic */ MapValue[] newArray(int i) {
        return new MapValue[i];
    }
}
