package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcdx extends zzbgl {
    public static final Parcelable.Creator<zzcdx> CREATOR = new zzcdy();
    private final String zzios;
    private final String zziot;
    private final String[] zziou;
    private final int[] zziov;
    private final int zziow;
    private final byte[] zziox;
    private final boolean zzioy;

    public zzcdx(String str, String str2, String[] strArr, int[] iArr, int i, byte[] bArr, boolean z) {
        this.zzios = str;
        this.zziot = str2;
        this.zziou = strArr;
        this.zziov = iArr;
        this.zziow = i;
        this.zziox = bArr;
        this.zzioy = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzios, false);
        zzbgo.zza(parcel, 4, this.zziot, false);
        zzbgo.zza(parcel, 5, this.zziou, false);
        zzbgo.zzc(parcel, 6, this.zziow);
        zzbgo.zza(parcel, 7, this.zziox, false);
        zzbgo.zza(parcel, 8, this.zziov, false);
        zzbgo.zza(parcel, 9, this.zzioy);
        zzbgo.zzai(parcel, zze);
    }
}
