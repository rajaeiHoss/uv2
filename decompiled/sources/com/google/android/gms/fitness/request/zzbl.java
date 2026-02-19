package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;

public final class zzbl extends zzbgl {
    public static final Parcelable.Creator<zzbl> CREATOR = new zzbm();
    private final String deviceAddress;
    private final zzbzt zzhnu;

    zzbl(String str, IBinder iBinder) {
        this.deviceAddress = str;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzbl(String str, zzbzt zzbzt) {
        this.deviceAddress = str;
        this.zzhnu = zzbzt;
    }

    public final String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", new Object[]{this.deviceAddress});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.deviceAddress, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 3, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
