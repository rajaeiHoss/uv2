package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i4 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i3 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zza(i, i2, i3);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}
