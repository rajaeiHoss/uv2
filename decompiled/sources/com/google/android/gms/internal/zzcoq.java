package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzcoq implements Parcelable.Creator<zzcop> {
    public final zzcop createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        String str = null;
        byte[] bArr = null;
        IBinder iBinder3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 2) {
                iBinder2 = zzbgm.zzr(parcel, readInt);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder3 = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcop(iBinder, iBinder2, str, bArr, iBinder3);
    }

    public final zzcop[] newArray(int i) {
        return new zzcop[i];
    }
}
