package com.google.android.gms.internal;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctl extends zzbgl {
    public static final Parcelable.Creator<zzctl> CREATOR = new zzctm();
    private String zzjwe;
    private String zzjwh;
    private BluetoothDevice zzjwi;
    private String zzjzi;

    private zzctl() {
    }

    public zzctl(String str, String str2, String str3, BluetoothDevice bluetoothDevice) {
        this.zzjzi = str;
        this.zzjwh = str2;
        this.zzjwe = str3;
        this.zzjwi = bluetoothDevice;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctl) {
            zzctl zzctl = (zzctl) obj;
            return zzbg.equal(this.zzjzi, zzctl.zzjzi) && zzbg.equal(this.zzjwh, zzctl.zzjwh) && zzbg.equal(this.zzjwe, zzctl.zzjwe) && zzbg.equal(this.zzjwi, zzctl.zzjwi);
        }
        return false;
    }

    public final String getEndpointName() {
        return this.zzjwe;
    }

    public final String getServiceId() {
        return this.zzjwh;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjzi, this.zzjwh, this.zzjwe, this.zzjwi});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjzi, false);
        zzbgo.zza(parcel, 2, this.zzjwh, false);
        zzbgo.zza(parcel, 3, this.zzjwe, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzjwi, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbdi() {
        return this.zzjzi;
    }

    public final BluetoothDevice zzbdj() {
        return this.zzjwi;
    }
}
