package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzcuj implements Parcelable.Creator<zzcui> {
    public final zzcui createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        String[] strArr = null;
        zzcub zzcub = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 2) {
                strArr = zzbgm.zzaa(parcel, readInt);
            } else if (i == 3) {
                zzcub = (zzcub) zzbgm.zza(parcel, readInt, zzcub.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcui(iBinder, strArr, zzcub, z);
    }

    public final zzcui[] newArray(int i) {
        return new zzcui[i];
    }
}
