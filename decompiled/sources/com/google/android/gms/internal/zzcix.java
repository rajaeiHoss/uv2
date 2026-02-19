package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzcix extends zzbgl {
    public static final Parcelable.Creator<zzcix> CREATOR = new zzciy();
    public final String name;
    public final String zzjgm;
    public final zzciu zzjhr;
    public final long zzjib;

    zzcix(zzcix zzcix, long j) {
        zzbq.checkNotNull(zzcix);
        this.name = zzcix.name;
        this.zzjhr = zzcix.zzjhr;
        this.zzjgm = zzcix.zzjgm;
        this.zzjib = j;
    }

    public zzcix(String str, zzciu zzciu, String str2, long j) {
        this.name = str;
        this.zzjhr = zzciu;
        this.zzjgm = str2;
        this.zzjib = j;
    }

    public final String toString() {
        String str = this.zzjgm;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzjhr);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.name, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzjhr, i, false);
        zzbgo.zza(parcel, 4, this.zzjgm, false);
        zzbgo.zza(parcel, 5, this.zzjib);
        zzbgo.zzai(parcel, zze);
    }
}
