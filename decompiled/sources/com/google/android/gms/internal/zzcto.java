package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcto implements Parcelable.Creator<zzctn> {
    public final zzctn createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctn(str);
    }

    public final zzctn[] newArray(int i) {
        return new zzctn[i];
    }
}
