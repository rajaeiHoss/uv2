package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.wearable.ConnectionConfiguration;

public final class zzdz implements Parcelable.Creator<zzdy> {
    public final zzdy createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ConnectionConfiguration[] connectionConfigurationArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                connectionConfigurationArr = (ConnectionConfiguration[]) zzbgm.zzb(parcel, readInt, ConnectionConfiguration.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdy(i, connectionConfigurationArr);
    }

    public final zzdy[] newArray(int i) {
        return new zzdy[i];
    }
}
