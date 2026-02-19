package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzbi implements Parcelable.Creator<zzay> {
    public final zzay createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str3 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzay(str, str2, str3);
    }

    public final zzay[] newArray(int i) {
        return new zzay[i];
    }
}
