package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzece extends zzbgl {
    public static final Parcelable.Creator<zzece> CREATOR = new zzecf();
    private final String zziva;
    private final long zzmsy;
    private final boolean zzmsz;
    private final String zzmta;

    public zzece(String str, long j, boolean z, String str2) {
        this.zziva = str;
        this.zzmsy = j;
        this.zzmsz = z;
        this.zzmta = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziva, false);
        zzbgo.zza(parcel, 2, this.zzmsy);
        zzbgo.zza(parcel, 3, this.zzmsz);
        zzbgo.zza(parcel, 4, this.zzmta, false);
        zzbgo.zzai(parcel, zze);
    }
}
