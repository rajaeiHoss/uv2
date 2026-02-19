package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzaum extends zzbgl {
    public static final Parcelable.Creator<zzaum> CREATOR = new zzaun();
    private boolean zzegf;

    public zzaum(boolean z) {
        this.zzegf = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzegf);
        zzbgo.zzai(parcel, zze);
    }
}
