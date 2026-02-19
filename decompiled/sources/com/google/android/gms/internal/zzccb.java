package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;

public final class zzccb implements Parcelable.Creator<zzcca> {
    public final zzcca createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataSource dataSource = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataSource = (DataSource) zzbgm.zza(parcel, readInt, DataSource.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcca(dataSource);
    }

    public final zzcca[] newArray(int i) {
        return new zzcca[i];
    }
}
