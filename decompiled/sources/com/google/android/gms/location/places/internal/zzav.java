package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzav implements Parcelable.Creator<zzau> {
    public final /* synthetic */ zzau createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 3) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 4) {
                str4 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 6) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 7) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzau(str, str2, str3, str4, i, i2);
    }

    public final /* synthetic */ zzau[] newArray(int i) {
        return new zzau[i];
    }
}
