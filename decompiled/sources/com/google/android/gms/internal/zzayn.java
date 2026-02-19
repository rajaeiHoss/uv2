package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzayn implements Parcelable.Creator<zzayp> {
    public final zzayp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
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
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzayp(str, str2, bArr);
    }

    public final zzayp[] newArray(int i) {
        return new zzayp[i];
    }
}
