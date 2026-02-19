package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbhx extends zzbgl {
    public static final Parcelable.Creator<zzbhx> CREATOR = new zzbhu();
    final String key;
    private int versionCode;
    final zzbhq<?, ?> zzgjc;

    zzbhx(int i, String str, zzbhq<?, ?> zzbhq) {
        this.versionCode = i;
        this.key = str;
        this.zzgjc = zzbhq;
    }

    zzbhx(String str, zzbhq<?, ?> zzbhq) {
        this.versionCode = 1;
        this.key = str;
        this.zzgjc = zzbhq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, this.key, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgjc, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
