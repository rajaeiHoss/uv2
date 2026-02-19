package com.google.android.gms.internal;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzctm implements Parcelable.Creator<zzctl> {
    public final zzctl createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        BluetoothDevice bluetoothDevice = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bluetoothDevice = (BluetoothDevice) zzbgm.zza(parcel, readInt, BluetoothDevice.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctl(str, str2, str3, bluetoothDevice);
    }

    public final zzctl[] newArray(int i) {
        return new zzctl[i];
    }
}
