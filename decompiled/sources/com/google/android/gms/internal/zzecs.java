package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecs implements Parcelable.Creator<zzecr> {
    public final zzecr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzecr(str, str2);
    }

    public final zzecr[] newArray(int i) {
        return new zzecr[i];
    }
}
