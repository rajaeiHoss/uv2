package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

@zzabh
public final class zzadj extends zzbgl {
    public static final Parcelable.Creator<zzadj> CREATOR = new zzadk();
    String zzbwz;

    public zzadj(String str) {
        this.zzbwz = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzbwz, false);
        zzbgo.zzai(parcel, zze);
    }
}
