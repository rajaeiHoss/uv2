package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.streamax.config.constant.Constants;
import java.util.Arrays;

public final class zzcux extends zzbgl {
    public static final Parcelable.Creator<zzcux> CREATOR = new zzcuy();
    private static final String zzkcj = null;
    public static final zzcux zzkck = new zzcux("", (String) null);
    private int zzehz;
    private final String zzkcl;
    private final String zzkcm;

    zzcux(int i, String str, String str2) {
        this.zzehz = ((Integer) zzbq.checkNotNull(Integer.valueOf(i))).intValue();
        this.zzkcl = str == null ? "" : str;
        this.zzkcm = str2;
    }

    private zzcux(String str, String str2) {
        this(1, str, (String) null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcux)) {
            return false;
        }
        zzcux zzcux = (zzcux) obj;
        return zzbg.equal(this.zzkcl, zzcux.zzkcl) && zzbg.equal(this.zzkcm, zzcux.zzkcm);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkcl, this.zzkcm});
    }

    public final String toString() {
        String str = this.zzkcl;
        String str2 = this.zzkcm;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(str2).length());
        sb.append("NearbyDevice{handle=");
        sb.append(str);
        sb.append(", bluetoothAddress=");
        sb.append(str2);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 3, this.zzkcl, false);
        zzbgo.zza(parcel, 6, this.zzkcm, false);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
