package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzbgm;

public final class zzf implements Parcelable.Creator<zze> {
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        BleDevice bleDevice = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                bleDevice = (BleDevice) zzbgm.zza(parcel, readInt, BleDevice.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zze(str, bleDevice, iBinder);
    }

    public final /* synthetic */ zze[] newArray(int i) {
        return new zze[i];
    }
}
