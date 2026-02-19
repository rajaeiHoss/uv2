package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecr extends zzbgl {
    public static final Parcelable.Creator<zzecr> CREATOR = new zzecs();
    private String zzmna;
    private String zzmvg;

    public zzecr(String str, String str2) {
        this.zzmvg = str;
        this.zzmna = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmvg, false);
        zzbgo.zza(parcel, 3, this.zzmna, false);
        zzbgo.zzai(parcel, zze);
    }
}
