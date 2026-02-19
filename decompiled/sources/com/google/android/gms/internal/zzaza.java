package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzaza implements Parcelable.Creator<zzayz> {
    public final zzayz createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                d = zzbgm.zzn(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzayz(i, d);
    }

    public final zzayz[] newArray(int i) {
        return new zzayz[i];
    }
}
