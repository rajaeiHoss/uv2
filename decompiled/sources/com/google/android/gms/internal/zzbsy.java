package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsy implements Parcelable.Creator<zzbsx> {
    public final zzbsx createFromParcel(Parcel parcel) {
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
        return new zzbsx(z);
    }

    public final zzbsx[] newArray(int i) {
        return new zzbsx[i];
    }
}
