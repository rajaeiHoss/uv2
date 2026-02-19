package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.nearby.messages.BleSignal;
import java.util.Arrays;

public final class zza extends zzbgl implements BleSignal {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private int zzehz;
    private int zzkcq;
    private int zzkcr;

    zza(int i, int i2, int i3) {
        this.zzehz = i;
        this.zzkcq = i2;
        this.zzkcr = (-169 >= i3 || i3 >= 87) ? Integer.MIN_VALUE : i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BleSignal)) {
            return false;
        }
        BleSignal bleSignal = (BleSignal) obj;
        return this.zzkcq == bleSignal.getRssi() && this.zzkcr == bleSignal.getTxPower();
    }

    public final int getRssi() {
        return this.zzkcq;
    }

    public final int getTxPower() {
        return this.zzkcr;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzkcq), Integer.valueOf(this.zzkcr)});
    }

    public final String toString() {
        int i = this.zzkcq;
        int i2 = this.zzkcr;
        StringBuilder sb = new StringBuilder(48);
        sb.append("BleSignal{rssi=");
        sb.append(i);
        sb.append(", txPower=");
        sb.append(i2);
        sb.append('}');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzc(parcel, 2, this.zzkcq);
        zzbgo.zzc(parcel, 3, this.zzkcr);
        zzbgo.zzai(parcel, zze);
    }
}
