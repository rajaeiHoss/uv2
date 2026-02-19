package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzctc implements Parcelable.Creator<zzctb> {
    public final zzctb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        byte[] bArr = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bArr = zzbgm.zzt(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctb(str, str2, str3, z, bArr);
    }

    public final zzctb[] newArray(int i) {
        return new zzctb[i];
    }
}
