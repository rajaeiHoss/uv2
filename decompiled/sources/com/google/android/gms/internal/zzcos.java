package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzcos implements Parcelable.Creator<zzcor> {
    public final zzcor createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        long j = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcor(iBinder, j);
    }

    public final zzcor[] newArray(int i) {
        return new zzcor[i];
    }
}
