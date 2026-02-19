package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsq extends zzbgl {
    public static final Parcelable.Creator<zzbsq> CREATOR = new zzbsr();
    final zzbte zzgxr;

    zzbsq(zzbte zzbte) {
        this.zzgxr = zzbte;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxr, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
