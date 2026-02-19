package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzaun implements Parcelable.Creator<zzaum> {
    public final zzaum createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaum(z);
    }

    public final zzaum[] newArray(int i) {
        return new zzaum[i];
    }
}
