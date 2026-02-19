package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcff extends zzbgl {
    public static final Parcelable.Creator<zzcff> CREATOR = new zzcfg();
    private final String packageName;
    private final int versionCode;
    private final String zziot;
    private final byte[] zziox;
    private final String zziqk;
    private final long zziql;

    public zzcff(String str, int i, String str2, String str3, long j, byte[] bArr) {
        this.packageName = str;
        this.versionCode = i;
        this.zziqk = str2;
        this.zziot = str3;
        this.zziql = j;
        this.zziox = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.packageName, false);
        zzbgo.zzc(parcel, 2, this.versionCode);
        zzbgo.zza(parcel, 3, this.zziqk, false);
        zzbgo.zza(parcel, 4, this.zziot, false);
        zzbgo.zza(parcel, 5, this.zziql);
        zzbgo.zza(parcel, 6, this.zziox, false);
        zzbgo.zzai(parcel, zze);
    }
}
