package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctg implements Parcelable.Creator<zzctf> {
    public final zzctf createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        byte[] bArr = null;
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
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctf(str, i, bArr);
    }

    public final zzctf[] newArray(int i) {
        return new zzctf[i];
    }
}
