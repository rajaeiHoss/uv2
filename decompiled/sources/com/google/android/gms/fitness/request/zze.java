package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;

public final class zze extends zzbgl {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    private final String deviceAddress;
    private final BleDevice zzhnt;
    private final zzbzt zzhnu;

    zze(String str, BleDevice bleDevice, IBinder iBinder) {
        this.deviceAddress = str;
        this.zzhnt = bleDevice;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zze(String str, BleDevice bleDevice, zzbzt zzbzt) {
        this.deviceAddress = str;
        this.zzhnt = bleDevice;
        this.zzhnu = zzbzt;
    }

    public final String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", new Object[]{this.deviceAddress, this.zzhnt});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.deviceAddress, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhnt, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 3, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
