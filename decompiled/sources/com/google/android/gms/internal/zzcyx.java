package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbt;

public final class zzcyx implements Parcelable.Creator<zzcyw> {
    public final zzcyw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ConnectionResult connectionResult = null;
        zzbt zzbt = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                connectionResult = (ConnectionResult) zzbgm.zza(parcel, readInt, ConnectionResult.CREATOR);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbt = (zzbt) zzbgm.zza(parcel, readInt, zzbt.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcyw(i, connectionResult, zzbt);
    }

    public final zzcyw[] newArray(int i) {
        return new zzcyw[i];
    }
}
