package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public final class zzbsw implements Parcelable.Creator<zzbsv> {
    public final zzbsv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        IBinder iBinder = null;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) zzbgm.zza(parcel, readInt, ParcelFileDescriptor.CREATOR);
            } else if (i == 3) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsv(parcelFileDescriptor, iBinder, str);
    }

    public final zzbsv[] newArray(int i) {
        return new zzbsv[i];
    }
}
