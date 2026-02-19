package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzfr implements Parcelable.Creator<zzfq> {
    public final zzfq createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        zzay zzay = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzay = (zzay) zzbgm.zza(parcel, readInt, zzay.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzfq(i, zzay);
    }

    public final zzfq[] newArray(int i) {
        return new zzfq[i];
    }
}
