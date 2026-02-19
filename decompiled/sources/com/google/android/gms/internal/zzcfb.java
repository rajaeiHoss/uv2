package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcfb extends zzbgl {
    public static final Parcelable.Creator<zzcfb> CREATOR = new zzcfc();
    private final String[] zziqb;
    private final String[] zziqc;
    private final String[] zziqd;
    private final String[] zziqe;

    public zzcfb(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
        this.zziqb = strArr;
        this.zziqc = strArr2;
        this.zziqe = strArr3;
        this.zziqd = strArr4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziqb, false);
        zzbgo.zza(parcel, 2, this.zziqc, false);
        zzbgo.zza(parcel, 3, this.zziqd, false);
        zzbgo.zza(parcel, 4, this.zziqe, false);
        zzbgo.zzai(parcel, zze);
    }
}
