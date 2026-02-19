package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzax implements Parcelable.Creator<zzaw> {
    public final zzaw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzay zzay = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 2) {
                zzay = (zzay) zzbgm.zza(parcel, readInt, zzay.CREATOR);
            } else if (i4 == 3) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 4) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i4 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i3 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaw(zzay, i, i2, i3);
    }

    public final zzaw[] newArray(int i) {
        return new zzaw[i];
    }
}
