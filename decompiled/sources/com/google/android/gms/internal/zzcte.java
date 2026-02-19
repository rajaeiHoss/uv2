package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcte implements Parcelable.Creator<zzctd> {
    public final zzctd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctd(str, str2, bArr);
    }

    public final zzctd[] newArray(int i) {
        return new zzctd[i];
    }
}
