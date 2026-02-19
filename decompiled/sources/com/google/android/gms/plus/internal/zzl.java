package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzl implements Parcelable.Creator<PlusCommonExtras> {
    public final /* synthetic */ PlusCommonExtras createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PlusCommonExtras(i, str, str2);
    }

    public final /* synthetic */ PlusCommonExtras[] newArray(int i) {
        return new PlusCommonExtras[i];
    }
}
