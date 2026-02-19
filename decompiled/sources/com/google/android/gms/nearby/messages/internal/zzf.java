package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzf implements Parcelable.Creator<zze> {
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        double d = 0.0d;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                d = zzbgm.zzn(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zze(i, i2, d);
    }

    public final /* synthetic */ zze[] newArray(int i) {
        return new zze[i];
    }
}
