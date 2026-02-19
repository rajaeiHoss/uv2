package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzn implements Parcelable.Creator<zzm> {
    public final zzm createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j2 = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzm(j, j2);
    }

    public final zzm[] newArray(int i) {
        return new zzm[i];
    }
}
