package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.wearable.ConnectionConfiguration;

public final class zzdx implements Parcelable.Creator<zzdw> {
    public final zzdw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ConnectionConfiguration connectionConfiguration = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                connectionConfiguration = (ConnectionConfiguration) zzbgm.zza(parcel, readInt, ConnectionConfiguration.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzdw(i, connectionConfiguration);
    }

    public final zzdw[] newArray(int i) {
        return new zzdw[i];
    }
}
