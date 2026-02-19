package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzj implements Parcelable.Creator<zzi> {
    public final zzi createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte b = 0;
        String str = null;
        byte b2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                b = zzbgm.zze(parcel, readInt);
            } else if (i == 3) {
                b2 = zzbgm.zze(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzi(b, b2, str);
    }

    public final zzi[] newArray(int i) {
        return new zzi[i];
    }
}
