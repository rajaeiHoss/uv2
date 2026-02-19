package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import java.util.Arrays;

public final class zzctr extends zzbgl {
    public static final Parcelable.Creator<zzctr> CREATOR = new zzcts();
    private String zzjxf;
    private PayloadTransferUpdate zzjzl;

    private zzctr() {
    }

    public zzctr(String str, PayloadTransferUpdate payloadTransferUpdate) {
        this.zzjxf = str;
        this.zzjzl = payloadTransferUpdate;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctr) {
            zzctr zzctr = (zzctr) obj;
            return zzbg.equal(this.zzjxf, zzctr.zzjxf) && zzbg.equal(this.zzjzl, zzctr.zzjzl);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, this.zzjzl});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzjzl, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }

    public final PayloadTransferUpdate zzbdm() {
        return this.zzjzl;
    }
}
