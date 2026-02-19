package com.google.android.gms.internal;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlo implements Parcelable.Creator<zzdln> {
    public final zzdln createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Rect rect = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                rect = (Rect) zzbgm.zza(parcel, readInt, Rect.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdln(rect);
    }

    public final zzdln[] newArray(int i) {
        return new zzdln[i];
    }
}
