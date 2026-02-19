package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbss implements Parcelable.Creator<zzbsn> {
    public final zzbsn createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsn(z);
    }

    public final zzbsn[] newArray(int i) {
        return new zzbsn[i];
    }
}
