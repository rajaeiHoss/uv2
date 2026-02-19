package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzq implements Parcelable.Creator<Field> {
    public final /* synthetic */ Field createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Boolean bool = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bool = zzbgm.zzd(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Field(str, i, bool);
    }

    public final /* synthetic */ Field[] newArray(int i) {
        return new Field[i];
    }
}
