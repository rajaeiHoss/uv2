package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzv implements Parcelable.Creator<DeviceMetaData> {
    public final DeviceMetaData createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i2 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z2 = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DeviceMetaData(i, z, j, z2);
    }

    public final DeviceMetaData[] newArray(int i) {
        return new DeviceMetaData[i];
    }
}
